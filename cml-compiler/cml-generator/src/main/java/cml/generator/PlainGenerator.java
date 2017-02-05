package cml.generator;

import cml.io.Console;
import cml.io.FileSystem;
import cml.model.Model;
import cml.model.ModelRepository;
import cml.model.Target;
import cml.templates.TemplateRenderer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

class PlainGenerator implements Generator
{
    private static final int SUCCESS = 0;
    private static final int FAILURE__TARGET_TYPE_UNDECLARED = 201;
    private static final int FAILURE__TARGET_TYPE_UNKNOWN = 202;

    private static final String MODULE = "module";

    private final Console console;
    private final ModelRepository modelRepository;
    private final TargetFileRepository targetFileRepository;
    private final TemplateRenderer templateRenderer;
    private final FileSystem fileSystem;

    PlainGenerator(
        final Console console,
        final FileSystem fileSystem,
        final ModelRepository modelRepository,
        final TargetFileRepository targetFileRepository, final TemplateRenderer templateRenderer)
    {
        this.console = console;
        this.fileSystem = fileSystem;
        this.modelRepository = modelRepository;
        this.targetFileRepository = targetFileRepository;
        this.templateRenderer = templateRenderer;
    }

    @Override
    public int generate(final Model model, final String targetType, final String targetDirPath)
    {
        final Optional<Target> target = modelRepository.getTarget(model, targetType);
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

        console.println("\nmodule files:");
        moduleFiles.forEach(targetFile -> renderTargetFile(target.get(), targetFile, targetDirPath));

        return SUCCESS;
    }

    private void renderTargetFile(final Target target, final TargetFile targetFile, final String targetDirPath)
    {
        console.println("- %s", targetFile.getPath());

        final Map<String, Object> args = new HashMap<>();
        args.put(target.getTargetType(), target);

        if (targetFile.getTemplateFile().isPresent())
        {
            final String path = targetDirPath + "/" + targetFile.getPath();
            final String contents = templateRenderer.renderTemplate(
                targetFile.getTemplateFile().get(),
                targetFile.getTemplateName(),
                args);

            fileSystem.createFile(path, contents);
        }
    }
}
