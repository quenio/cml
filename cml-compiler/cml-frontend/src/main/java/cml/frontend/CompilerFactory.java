package cml.frontend;

import cml.generator.Generator;
import cml.generator.TargetRepository;
import cml.io.Console;
import cml.io.FileSystem;
import cml.model.ModelBuilder;
import cml.parser.Parser;

import static cml.generator.GeneratorFactory.createGenerator;
import static cml.generator.TargetRepositoryFactory.createTargetRepository;
import static cml.io.ConsoleFactory.createConsole;
import static cml.io.FileSystemFactory.createFileSystem;
import static cml.model.ModelBuilderFactory.createModelBuilder;
import static cml.parser.ParserFactory.createParser;

class CompilerFactory
{
    static Compiler createCompiler()
    {
        final Console console = createConsole();
        final FileSystem fileSystem = createFileSystem();
        final ModelBuilder modelBuilder = createModelBuilder();
        final Parser parser = createParser(console, fileSystem, modelBuilder);
        final TargetRepository targetRepository = createTargetRepository();
        final Generator generator = createGenerator(targetRepository);
        return new PlainCompiler(console, fileSystem, parser, targetRepository, generator);
    }
}
