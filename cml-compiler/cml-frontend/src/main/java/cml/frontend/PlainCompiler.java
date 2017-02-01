package cml.frontend;

import cml.generator.Generator;
import cml.generator.Target;
import cml.io.Console;
import cml.io.Directory;
import cml.io.FileSystem;

import java.util.Optional;

class PlainCompiler implements Compiler
{
    private static final int SUCCESS = 0;
    private static final int FAILURE__SOURCE_DIR_NOT_FOUND = 1;
    private static final int FAILURE__UNKNOWN_TARGET_TYPE = 2;

    private final Console console;
    private final FileSystem fileSystem;
    private final Generator generator;

    PlainCompiler(Console console, FileSystem fileSystem, Generator generator)
    {
        this.console = console;
        this.fileSystem = fileSystem;
        this.generator = generator;
    }

    @Override
    public int compile(String sourceDirPath, String targetDirPath, String targetType)
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

        console.println("source dir = %s", sourceDirPath);
        console.println("target dir = %s", targetDirPath);
        console.println("target type = %s", targetType);

        return SUCCESS;
    }
}
