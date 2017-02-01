package cml.frontend;

import cml.generator.Generator;
import cml.generator.PlainGenerator;
import cml.io.Console;
import cml.io.FileSystem;
import cml.io.PlainConsole;
import cml.io.PlainFileSystem;

public class Launcher
{
    public static void main(String[] args)
    {
        final Console console = new PlainConsole();
        final FileSystem fileSystem = new PlainFileSystem();
        final Generator generator = new PlainGenerator();
        final Compiler compiler = new PlainCompiler(console, fileSystem, generator);

        final String sourceDirPath = args[0];
        final String targetDirPath = args[1];
        final String targetType = args[2];

        final int exitCode = compiler.compile(sourceDirPath, targetDirPath, targetType);

        System.exit(exitCode);
    }
}
