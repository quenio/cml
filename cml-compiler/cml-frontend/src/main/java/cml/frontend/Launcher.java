package cml.frontend;

public final class Launcher
{
    private Launcher()
    {
    }

    public static void main(final String... args)
    {
        final String sourceDirPath = args[0];
        final String targetDirPath = args[1];
        final String targetType = args[2];

        final Compiler compiler = Compiler.create();
        final int exitCode = compiler.compile(sourceDirPath, targetDirPath, targetType);

        System.exit(exitCode);
    }
}
