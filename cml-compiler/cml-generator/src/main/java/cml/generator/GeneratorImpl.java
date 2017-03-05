package cml.generator;

import cml.io.Console;
import cml.io.Directory;
import cml.io.FileSystem;
import cml.language.features.Concept;
import cml.language.features.Model;
import cml.language.features.Target;
import cml.templates.TemplateRenderer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

class GeneratorImpl implements Generator
{
    private static final int SUCCESS = 0;
    private static final int FAILURE__TARGET_TYPE_UNKNOWN = 101;
    private static final int FAILURE__TARGET_TYPE_UNDECLARED = 102;

    private static final String MODULE = "module";
    private static final String CONCEPT = "concept";

    private final Console console;
    private final TargetFileRepository targetFileRepository;
    private final TemplateRenderer templateRenderer;
    private final FileSystem fileSystem;

    GeneratorImpl(
        Console console,
        FileSystem fileSystem,
        TargetFileRepository targetFileRepository,
        TemplateRenderer templateRenderer)
    {
        this.console = console;
        this.fileSystem = fileSystem;
        this.targetFileRepository = targetFileRepository;
        this.templateRenderer = templateRenderer;
    }

    @Override
    public int generate(Model model, final String targetType, final String targetDirPath)
    {
        final Optional<Target> target = model.getTarget(targetType);
        if (!target.isPresent())
        {
            console.println("Source files have not declared target type: %s", targetType);
            return FAILURE__TARGET_TYPE_UNDECLARED;
        }

        final Map<String, Object> templateArgs = new HashMap<>();
        templateArgs.put(targetType, getTargetProperties(target.get()));

        final Set<TargetFile> moduleFiles = findModuleFiles(target.get(), templateArgs);

        if (moduleFiles.isEmpty())
        {
            console.println("No templates found for target type: %s", targetType);
            return FAILURE__TARGET_TYPE_UNKNOWN;
        }

        final Optional<Directory> targetDir = fileSystem.findDirectory(targetDirPath);
        targetDir.ifPresent(fileSystem::cleanDirectory);

        console.println("module files:");
        moduleFiles.forEach(targetFile -> renderTargetFile(targetFile, targetDirPath, templateArgs));

        for (Concept concept: model.getConcepts())
        {
            templateArgs.put(CONCEPT, concept);

            final Set<TargetFile> conceptFiles = findConceptFiles(target.get(), templateArgs);

            console.println("\n%s files:", concept.getName());
            conceptFiles.forEach(targetFile -> renderTargetFile(targetFile, targetDirPath, templateArgs));
        }

        return SUCCESS;
    }

    private Set<TargetFile> findModuleFiles(final Target target, final Map<String, Object> templateArgs)
    {
        return targetFileRepository.findTargetFiles(target, MODULE, templateArgs);
    }

    private Set<TargetFile> findConceptFiles(final Target target, final Map<String, Object> templateArgs)
    {
        return targetFileRepository.findTargetFiles(target, CONCEPT, templateArgs);
    }

    private void renderTargetFile(
        final TargetFile targetFile,
        final String targetDirPath,
        final Map<String, Object> args)
    {
        console.println("- %s", targetFile.getPath());

        if (targetFile.getTemplateFile().isPresent())
        {
            final String path = targetDirPath + File.separatorChar + targetFile.getPath();
            final String contents = templateRenderer.renderTemplate(
                targetFile.getTemplateFile().get(),
                targetFile.getTemplateName(),
                args);

            fileSystem.createFile(path, contents);
        }
    }

    private static Map<String, Object> getTargetProperties(final Target target)
    {
        final Map<String, Object> properties = new HashMap<>();

        target.getProperties().forEach(property -> properties.put(property.getName(), property.getValue()));

        return properties;
    }
}
