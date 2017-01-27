package cml.acceptance;

import org.apache.maven.shared.invoker.*;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.WriterStreamConsumer;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

import static java.util.Arrays.asList;
import static org.codehaus.plexus.util.cli.CommandLineUtils.executeCommandLine;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CompilerCommandTests
{
    private static final Charset OUTPUT_FILE_ENCODING = Charset.forName("UTF-8");
    
    private static final String FRONTEND_DIR = "../cml-frontend";
    private static final String TARGET_DIR = FRONTEND_DIR + "/target";
    private static final String JAR_NAME = "cml-compiler-jar-with-dependencies.jar";
    
    private static final String EXPECTED_OUTPUT =
        "---\n" +
        "source dir = source\n" +
        "target dir = target\n" +
        "target type = type\n";

    @Before
    public void setUp() throws Exception
    {
        final File frontendDir = new File(FRONTEND_DIR);
        assertThat("Frontend dir must exist: " + frontendDir, frontendDir.exists(), is(true));

        buildModule(frontendDir, "package");
    }

    @Test
    public void verifyArguments() throws Exception
    {
        final String actualOutput = compile(asList("source", "target", "type"));

        assertThat("compiler's output", actualOutput, is(EXPECTED_OUTPUT));
    }

    private String compile(List<String> args) throws CommandLineException, IOException
    {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try
        {
            final int exitCode = executeJar(args, outputStream);

            assertThat("exit code", exitCode, is(0));
        }
        finally
        {
            outputStream.close();
        }

        return new String(outputStream.toByteArray(), OUTPUT_FILE_ENCODING);
    }

    private static void buildModule(File baseDir, String... goals) throws MavenInvocationException
    {
        System.setProperty("maven.home", System.getenv("M2_HOME"));

        final InvocationRequest request = new DefaultInvocationRequest();
        request.setBaseDirectory(baseDir);
        request.setGoals(asList(goals));
        request.setInteractive(false);

        final Invoker invoker = new DefaultInvoker();
        final InvocationResult result = invoker.execute(request);

        if (result.getExitCode() != 0) throw new MavenInvocationException("Exit code: " + result.getExitCode());
    }

    private int executeJar(List<String> args, OutputStream outputStream) throws CommandLineException
    {
        final File targetDir = new File(TARGET_DIR);
        assertThat("Target dir must exist: " + targetDir, targetDir.exists(), is(true));

        final File jarFile = new File(targetDir, JAR_NAME);
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

        println(writer, "---"); // Separating compilation from execution output.

        final int exitCode = executeCommandLine(commandLine, systemOut, systemErr, 10);

        System.out.println("Jar's exit code: " + exitCode);

        return exitCode;
    }

    @SuppressWarnings("SameParameterValue")
    private void println(Writer writer, String line)
    {
        new PrintWriter(writer).println(line);
    }

}
