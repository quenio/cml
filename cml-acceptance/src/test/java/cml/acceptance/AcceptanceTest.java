package cml.acceptance;

import com.google.common.io.Files;
import org.apache.maven.shared.invoker.*;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.WriterStreamConsumer;
import org.junit.Before;
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
import static org.codehaus.plexus.util.FileUtils.cleanDirectory;
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

    private static final String CLIENT_DIR = BASE_DIR + "/java-clients/livir-console";
    private static final String CLIENT_TARGET_DIR = CLIENT_DIR + "/target";
    private static final String CLIENT_JAR = CLIENT_TARGET_DIR + "/livir-console-jar-with-dependencies.jar";

    private static final String CASES_DIR = "cases";
    private static final String COMPILER_OUTPUT_FILENAME = "/compiler-output.txt";
    private static final String CLIENT_OUTPUT_FILENAME = "/client-output.txt";

    private static final String TARGET_DIR = "target/poj";
    private static final String TARGET_TYPE = "poj";

    @DataPoints("cases")
    public static String[] cases = { "livir-books" };

    @Before
    public void setUp() throws Exception
    {
        buildModule(COMPILER_DIR, "clean", "install");

        final File targetDir = new File(FRONTEND_TARGET_DIR);
        assertThat("Target dir must exist: " + targetDir, targetDir.exists(), is(true));
    }

    @Theory
    public void poj(@FromDataPoints("cases") String caseName) throws Exception
    {
        cleanDirectory(TARGET_DIR);
        assertThat("Target dir must not exist: " + TARGET_DIR, new File(TARGET_DIR).exists(), is(true));

        final String sourceDir = CASES_DIR + "/" + caseName;
        final String actualCompilerOutput = executeJar(COMPILER_JAR, asList(sourceDir, TARGET_DIR, TARGET_TYPE));
        assertThatOutputMatches(
            "compiler's output",
            sourceDir + COMPILER_OUTPUT_FILENAME,
            actualCompilerOutput);

        buildModule(TARGET_DIR, "clean", "install");
        buildModule(CLIENT_DIR, "clean", "install");

        final File clientDir = new File(CLIENT_TARGET_DIR);
        assertThat("Client dir must exist: " + clientDir, clientDir.exists(), is(true));

        final String actualClientOutput = executeJar(CLIENT_JAR, emptyList());
        assertThatOutputMatches(
            "client's output",
            sourceDir + CLIENT_OUTPUT_FILENAME,
            actualClientOutput);
    }

    private void assertThatOutputMatches(
        final String reason,
        final String expectedOutputPath,
        final String actualOutput) throws IOException
    {
        final String expectedOutput = Files.toString(new File(expectedOutputPath), OUTPUT_FILE_ENCODING);
        assertThat(reason, actualOutput, is(expectedOutput));
    }

    private static void buildModule(final String baseDir, final String... goals) throws MavenInvocationException
    {
        System.setProperty("maven.home", System.getenv("M2_HOME"));

        final InvocationRequest request = new DefaultInvocationRequest();
        request.setBaseDirectory(new File(baseDir));
        request.setGoals(asList(goals));
        request.setInteractive(false);

        final Invoker invoker = new DefaultInvoker();
        final InvocationResult result = invoker.execute(request);

        if (result.getExitCode() != 0) throw new MavenInvocationException("Exit code: " + result.getExitCode());
    }

    private String executeJar(final String jarPath, final List<String> args) throws CommandLineException, IOException
    {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try
        {
            final int exitCode = executeJar(jarPath, args, outputStream);

            assertThat("exit code", exitCode, is(0));
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

        for (final String arg: args) commandLine.createArg().setValue(arg);

        final Writer writer = new OutputStreamWriter(outputStream);
        final WriterStreamConsumer systemOut = new WriterStreamConsumer(writer);
        final WriterStreamConsumer systemErr = new WriterStreamConsumer(writer);

        System.out.println("Launching jar: " + commandLine);

        final int exitCode = executeCommandLine(commandLine, systemOut, systemErr, 10);

        System.out.println("Jar's exit code: " + exitCode);
        System.out
            .println("Output: \n\n---\n" + new String(outputStream.toByteArray(), OUTPUT_FILE_ENCODING) + "---\n");

        return exitCode;
    }

    @SuppressWarnings("SameParameterValue")
    private void println(final Writer writer, final String line)
    {
        new PrintWriter(writer).println(line);
    }

}
