package cml.frontend;

import static cml.frontend.CompilerFactory.createCompiler;

public class Launcher
{
    public static void main(final String[] args)
    {
        final String sourceDirPath = args[0];
        final String targetDirPath = args[1];
        final String targetType = args[2];

        final Compiler compiler = createCompiler();
        final int exitCode = compiler.compile(sourceDirPath, targetDirPath, targetType);

        System.exit(exitCode);
    }
}
