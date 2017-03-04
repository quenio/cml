package cml.frontend;

import cml.generator.Generator;
import cml.io.Console;
import cml.io.FileSystem;
import cml.language.grammar.ModelLoader;

import static cml.generator.GeneratorFactory.createGenerator;
import static cml.io.ConsoleFactory.createConsole;
import static cml.io.FileSystemFactory.createFileSystem;

class CompilerFactory
{
    static Compiler createCompiler()
    {
        final Console console = createConsole();
        final FileSystem fileSystem = createFileSystem();
        final ModelLoader modelLoader = ModelLoader.create(console);
        final Generator generator = createGenerator(console, fileSystem);
        return new PlainCompiler(console, fileSystem, modelLoader, generator);
    }
}
