package cml.generator;

import cml.io.Console;
import cml.model.Model;
import cml.model.ModelQueries;
import cml.model.Target;

import java.util.Optional;
import java.util.Set;

class PlainGenerator implements Generator
{
    private static final int SUCCESS = 0;
    private static final int FAILURE__TARGET_TYPE_UNDECLARED = 201;
    private static final int FAILURE__TARGET_TYPE_UNKNOWN = 202;

    private static final String MODULE = "module";

    private final Console console;
    private final ModelQueries modelQueries;
    private final TargetFileRepository targetFileRepository;

    PlainGenerator(
        final Console console,
        final ModelQueries modelQueries,
        final TargetFileRepository targetFileRepository)
    {
        this.console = console;
        this.modelQueries = modelQueries;
        this.targetFileRepository = targetFileRepository;
    }

    @Override
    public int generate(final Model model, final String targetType, final String targetDirPath)
    {
        final Optional<Target> target = modelQueries.getTarget(model, targetType);
        if (!target.isPresent())
        {
            console.println("Source files have not declared target type: %s", targetType);
            return FAILURE__TARGET_TYPE_UNDECLARED;
        }

        final Set<TargetFile> moduleFiles = targetFileRepository.findTargetFiles(target.get(), MODULE);

        if (moduleFiles.isEmpty())
        {
            console.println("No templates found for target type: %s", targetType);
            return FAILURE__TARGET_TYPE_UNKNOWN;
        }

        return SUCCESS;
    }
}
