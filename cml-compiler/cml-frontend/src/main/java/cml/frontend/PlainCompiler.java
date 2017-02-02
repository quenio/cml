package cml.frontend;

import cml.generator.Generator;
import cml.generator.Target;
import cml.io.Console;
import cml.io.Directory;
import cml.io.FileSystem;
import cml.model.Concept;
import cml.model.Model;
import cml.parser.Parser;

import java.util.Optional;

class PlainCompiler implements Compiler
{
    private static final int SUCCESS = 0;
    private static final int FAILURE__SOURCE_DIR_NOT_FOUND = 1;
    private static final int FAILURE__UNKNOWN_TARGET_TYPE = 2;
    private static final int FAILURE__PARSING_FAILED = 3;

    private final Console console;
    private final FileSystem fileSystem;
    private final Parser parser;
    private final Generator generator;

    PlainCompiler(final Console console, final FileSystem fileSystem, final Parser parser, final Generator generator)
    {
        this.console = console;
        this.fileSystem = fileSystem;
        this.parser = parser;
        this.generator = generator;
    }

    @Override
    public int compile(final String sourceDirPath, final String targetDirPath, final String targetType)
    {
        final Optional<Directory> sourceDir = fileSystem.findDirectory(sourceDirPath);
        if (!sourceDir.isPresent())
        {
            console.println("Source dir missing: %s", sourceDirPath);
            return FAILURE__SOURCE_DIR_NOT_FOUND;
        }

        final Optional<Target> target = generator.findTarget(targetType, targetDirPath);
        if (!target.isPresent())
        {
            console.println("Unknown target type: %s", targetType);
            return FAILURE__UNKNOWN_TARGET_TYPE;
        }

        console.println("source dir: %s", sourceDirPath);
        console.println("target dir: %s", targetDirPath);
        console.println("target type: %s", targetType);

        final Optional<Model> model = parser.parse(sourceDir.get());
        if (model.isPresent())
        {
            console.println("concepts:");
            for (final Concept concept : model.get().getConcepts())
            {
                console.println("- %s", concept.getName());
            }
        }
        else
        {
            console.println("Unable to parse source files.");
            return FAILURE__PARSING_FAILED;
        }

        return SUCCESS;
    }
}
