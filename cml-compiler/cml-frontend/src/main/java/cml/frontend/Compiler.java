package cml.frontend;

import cml.generator.Generator;
import cml.io.Console;
import cml.io.FileSystem;
import cml.language.ModelLoader;

public interface Compiler
{
    int compile(String sourceDirPath, String targetDirPath, String targetType);

    static Compiler create()
    {
        final Console console = Console.create();
        final FileSystem fileSystem = FileSystem.create();
        final ModelLoader modelLoader = ModelLoader.create(console);
        final Generator generator = Generator.create(console, fileSystem);
        return new CompilerImpl(console, fileSystem, modelLoader, generator);
    }
}
