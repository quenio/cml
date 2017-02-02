package cml.parser;

import cml.antlr.CMLLexer;
import cml.antlr.CMLParser;
import cml.io.Console;
import cml.io.Directory;
import cml.io.FileSystem;
import cml.io.SourceFile;
import cml.model.Model;
import cml.model.ModelBuilder;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import static java.util.Optional.empty;

class PlainParser implements Parser
{
    private static final String MAIN_SOURCE = "main.cml";

    private final Console console;
    private final FileSystem fileSystem;
    private final ModelBuilder modelBuilder;

    PlainParser(final Console console, final FileSystem fileSystem, final ModelBuilder modelBuilder)
    {
        this.console = console;
        this.fileSystem = fileSystem;
        this.modelBuilder = modelBuilder;
    }

    @Override
    public Optional<Model> parse(final Directory sourceDir)
    {
        final Optional<SourceFile> sourceFile = fileSystem.findSourceFile(sourceDir, MAIN_SOURCE);
        if (!sourceFile.isPresent())
        {
            console.println(
                "Main source file (%s) missing in source dir: %s", MAIN_SOURCE,
                sourceDir.getPath());
            return empty();
        }

        final String mainSourcePath = sourceFile.get().getPath();
        
        try (final FileInputStream fileInputStream = new FileInputStream(mainSourcePath))
        {
            final ANTLRInputStream input = new ANTLRInputStream(fileInputStream);
            final CMLLexer lexer = new CMLLexer(input);
            final CommonTokenStream tokens = new CommonTokenStream(lexer);
            final CMLParser parser = new CMLParser(tokens);
            final CMLParser.StartContext startContext = parser.start();
            final ParseTreeWalker walker = new ParseTreeWalker();
            final ModelSynthesiser synthesiser = new ModelSynthesiser(modelBuilder);

            walker.walk(synthesiser, startContext);

            return Optional.of(modelBuilder.buildModel());
        }
        catch (final IOException exception)
        {
            console.println("I/O Error: %s", exception.getMessage());
            return empty();
        }
    }
}
