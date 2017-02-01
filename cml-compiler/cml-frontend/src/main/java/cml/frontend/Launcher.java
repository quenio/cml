package cml.frontend;

import cml.generator.Generator;
import cml.generator.PlainGenerator;
import cml.io.Console;
import cml.io.FileSystem;
import cml.io.PlainConsole;
import cml.io.PlainFileSystem;
import cml.model.ModelBuilder;
import cml.model.PlainModelBuilder;
import cml.parser.Parser;
import cml.parser.PlainParser;

public class Launcher
{
    public static void main(final String[] args)
    {
        final Console console = new PlainConsole();
        final FileSystem fileSystem = new PlainFileSystem();
        final ModelBuilder modelBuilder = new PlainModelBuilder();
        final Parser parser = new PlainParser(console, fileSystem, modelBuilder);
        final Generator generator = new PlainGenerator();
        final Compiler compiler = new PlainCompiler(console, fileSystem, parser, generator);

        final String sourceDirPath = args[0];
        final String targetDirPath = args[1];
        final String targetType = args[2];

        final int exitCode = compiler.compile(sourceDirPath, targetDirPath, targetType);

        System.exit(exitCode);
    }
}
