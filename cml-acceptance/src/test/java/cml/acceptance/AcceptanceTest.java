package cml.acceptance;

import com.google.common.io.Files;
import org.apache.maven.shared.invoker.*;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.WriterStreamConsumer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.codehaus.plexus.util.FileUtils.*;
import static org.codehaus.plexus.util.cli.CommandLineUtils.executeCommandLine;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(Theories.class)
public class AcceptanceTest
{
    private static final Charset OUTPUT_FILE_ENCODING = Charset.forName("UTF-8");

    private static final String BASE_DIR = "..";

    private static final String COMPILER_DIR = BASE_DIR + "/cml-compiler";
    private static final String FRONTEND_DIR = COMPILER_DIR + "/cml-frontend";
    private static final String FRONTEND_TARGET_DIR = FRONTEND_DIR + "/target";
    private static final String COMPILER_JAR = FRONTEND_TARGET_DIR + "/cml-compiler-jar-with-dependencies.jar";

    private static final String CLIENT_BASE_DIR = BASE_DIR + "/java-clients";
    private static final String CLIENT_JAR_SUFFIX = "-jar-with-dependencies.jar";

    private static final String CASES_DIR = "cases";
    private static final String COMPILER_OUTPUT_FILENAME = "/compiler-output.txt";
    private static final String CLIENT_OUTPUT_FILENAME = "/client-output.txt";

    private static final String TARGET_DIR = "target/acceptance-test";
    private static final String POJ = "poj"; // plain old Java

    private static final int SUCCESS = 0;
    private static final int FAILURE__SOURCE_DIR_NOT_FOUND = 1;
    private static final int FAILURE__SOURCE_FILE_NOT_FOUND = 2;
    private static final int FAILURE__PARSING_FAILED = 3;
    private static final int FAILURE__TARGET_TYPE_UNKNOWN = 101;
    private static final int FAILURE__TARGET_TYPE_UNDECLARED = 102;

    @DataPoints("success-cases")
    public static Case[] successCases = {
        new Case("livir-books", "poj", "livir-console"),
        new Case("mini-cml-language", "cmlc", "mcml-compiler")
    };

    @BeforeClass
    public static void buildCompiler() throws MavenInvocationException
    {
        buildModule(COMPILER_DIR);

        final File targetDir = new File(FRONTEND_TARGET_DIR);
        assertThat("Target dir must exist: " + targetDir, targetDir.exists(), is(true));
    }

    @Before
    public void cleanTargetDir() throws IOException
    {
        System.out.println("\n--------------------------");
        System.out.println("Cleaning target dir: " + TARGET_DIR);

        forceMkdir(new File(TARGET_DIR));
        cleanDirectory(TARGET_DIR);
    }

    @Theory
    public void successCases(@FromDataPoints("success-cases") final Case successCase) throws Exception
    {
        final String sourceDir = CASES_DIR + "/" + successCase.getName();

        compileAndVerifyOutput(sourceDir, successCase.getTargetType(), SUCCESS);
        buildModule(TARGET_DIR);

        final String clientModuleDir = CLIENT_BASE_DIR + "/" + successCase.getJavaClient();
        buildModule(clientModuleDir);

        final File clientTargetDir = new File(clientModuleDir, "target");
        assertThat("Client target dir must exist: " + clientTargetDir, clientTargetDir.exists(), is(true));

        final String clientJarPath = clientTargetDir.getPath() + "/" + successCase.getJavaClient() + CLIENT_JAR_SUFFIX;
        final String actualClientOutput = executeJar(clientJarPath, emptyList(), SUCCESS);
        assertThatOutputMatches(
            "client's output",
            sourceDir + CLIENT_OUTPUT_FILENAME,
            actualClientOutput);
    }

    @Test
    public void missing_source_dir() throws Exception
    {
        compileAndVerifyOutput(
            CASES_DIR + "/unknown-dir",
            POJ,
            CASES_DIR + "/missing-source-dir",
            FAILURE__SOURCE_DIR_NOT_FOUND);
    }

    @Test
    public void missing_source_file() throws Exception
    {
        compileAndVerifyOutput(CASES_DIR + "/missing-source", POJ, FAILURE__SOURCE_FILE_NOT_FOUND);
    }

    @Test
    public void target_type_unknown() throws Exception
    {
        compileWithTargetTypeAndVerifyOutput(
            CASES_DIR + "/target-type-unknown",
            "unknown_target",
            FAILURE__TARGET_TYPE_UNKNOWN);
    }

    @Test
    public void target_type_undeclared() throws Exception
    {
        compileAndVerifyOutput(
            CASES_DIR + "/target-type-undeclared",
            POJ, 
            FAILURE__TARGET_TYPE_UNDECLARED);
    }

    @Test
    public void target_dir_created() throws Exception
    {
        final File targetDir = new File(TARGET_DIR);

        forceDelete(targetDir);
        assertThat("Target dir must NOT exist: " + targetDir, targetDir.exists(), is(false));

        compileAndVerifyOutput(CASES_DIR + "/target-dir-created", POJ, SUCCESS);
        assertThat("Target dir must exist: " + targetDir, targetDir.exists(), is(true));
    }

    @Test
    public void target_dir_cleaned() throws Exception
    {
        final File bookFile = new File(TARGET_DIR, "src/main/java/books/Book.java");
        final File bookStoreFile = new File(TARGET_DIR, "src/main/java/books/BookStore.java");

        // Ensures there is already content in the target dir:
        compileAndVerifyOutput(CASES_DIR + "/target-dir-created", POJ, SUCCESS);
        assertThat("Book must exist: " + bookFile, bookFile.exists(), is(true));
        assertThat("BookStore must NOT exist: " + bookFile, bookStoreFile.exists(), is(false));

        // Verifies that the previously generated target has been cleaned before generating the new one:
        compileAndVerifyOutput(CASES_DIR + "/target-dir-cleaned", POJ, SUCCESS);
        assertThat("Book must NOT exist: " + bookFile, bookFile.exists(), is(false));
        assertThat("BookStore must exist: " + bookFile, bookStoreFile.exists(), is(true));
    }

    private void compileAndVerifyOutput(
        final String sourceDir,
        final String targetType,
        final int expectedExitCode) throws CommandLineException, IOException
    {
        compileWithTargetTypeAndVerifyOutput(sourceDir, targetType, expectedExitCode);
    }

    private void compileWithTargetTypeAndVerifyOutput(
        final String sourceDir,
        final String targetType,
        final int expectedExitCode) throws CommandLineException, IOException
    {
        compileAndVerifyOutput(sourceDir, targetType, sourceDir, expectedExitCode);
    }

    private void compileAndVerifyOutput(
        final String sourceDir,
        final String targetType,
        final String expectedOutputDir,
        final int expectedExitCode) throws CommandLineException, IOException
    {
        final String actualCompilerOutput = executeJar(
            COMPILER_JAR, asList(sourceDir, TARGET_DIR, targetType), expectedExitCode);

        assertThatOutputMatches(
            "compiler's output",
            expectedOutputDir + COMPILER_OUTPUT_FILENAME,
            actualCompilerOutput);
    }

    private void assertThatOutputMatches(
        final String reason,
        final String expectedOutputPath,
        final String actualOutput) throws IOException
    {
        final String expectedOutput = Files.toString(new File(expectedOutputPath), OUTPUT_FILE_ENCODING);
        assertThat(reason, actualOutput, is(expectedOutput));
    }

    private static void buildModule(final String baseDir) throws MavenInvocationException
    {
        System.out.println("Building: " + baseDir);

        System.setProperty("maven.home", System.getenv("M2_HOME"));

        final InvocationRequest request = new DefaultInvocationRequest();
        request.setBaseDirectory(new File(baseDir));
        request.setGoals(asList("-q", "clean", "install"));
        request.setInteractive(false);

        final Invoker invoker = new DefaultInvoker();
        final InvocationResult result = invoker.execute(request);

        if (result.getExitCode() != 0) throw new MavenInvocationException("Exit code: " + result.getExitCode());
    }

    private String executeJar(
        final String jarPath,
        final List<String> args,
        final int expectedExitCode) throws CommandLineException, IOException
    {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try
        {
            final int actualExitCode = executeJar(jarPath, args, outputStream);

            assertThat("exit code", actualExitCode, is(expectedExitCode));
        }
        finally
        {
            outputStream.close();
        }

        return new String(outputStream.toByteArray(), OUTPUT_FILE_ENCODING);
    }

    private int executeJar(
        final String jarPath,
        final List<String> args,
        final ByteArrayOutputStream outputStream) throws CommandLineException
    {
        final File jarFile = new File(jarPath);
        assertThat("Jar file must exit: " + jarFile, jarFile.exists(), is(true));

        final File javaBinDir = new File(System.getProperty("java.home"), "bin");
        assertThat("Java bin dir must exit: " + javaBinDir, javaBinDir.exists(), is(true));

        final File javaExecFile = new File(javaBinDir, "java");
        assertThat("Java exec file must exit: " + javaExecFile, javaExecFile.exists(), is(true));

        final Commandline commandLine = new Commandline();
        commandLine.setExecutable(javaExecFile.getAbsolutePath());

        commandLine.createArg().setValue("-jar");
        commandLine.createArg().setValue(jarFile.getAbsolutePath());

        for (final String arg : args) commandLine.createArg().setValue(arg);

        final Writer writer = new OutputStreamWriter(outputStream);
        final WriterStreamConsumer systemOut = new WriterStreamConsumer(writer);
        final WriterStreamConsumer systemErr = new WriterStreamConsumer(writer);

        System.out.println("Launching jar: " + commandLine);

        final int exitCode = executeCommandLine(commandLine, systemOut, systemErr, 10);

        System.out.println("Jar's exit code: " + exitCode);
        System.out.println("Output: \n---\n" + new String(outputStream.toByteArray(), OUTPUT_FILE_ENCODING) + "---\n");

        return exitCode;
    }

}
