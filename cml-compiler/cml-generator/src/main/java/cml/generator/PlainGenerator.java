package cml.generator;

import cml.io.Console;
import cml.io.FileSystem;
import cml.model.Concept;
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
    private static final String CONCEPT = "concept";

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

        final Map<String, Object> templateArgs = new HashMap<>();
        templateArgs.put(targetType, getTargetProperties(target.get()));

        final Set<TargetFile> moduleFiles = findModuleFiles(target.get(), templateArgs);

        if (moduleFiles.isEmpty())
        {
            console.println("No templates found for target type: %s", targetType);
            return FAILURE__TARGET_TYPE_UNKNOWN;
        }

        console.println("\nmodule files:");
        moduleFiles.forEach(targetFile -> renderTargetFile(targetFile, targetDirPath, templateArgs));

        final Set<Concept> concepts = modelRepository.getConcepts(model);
        concepts.forEach(
            concept ->
            {
                templateArgs.put(CONCEPT, concept);

                final Set<TargetFile> conceptFiles = findConceptFiles(target.get(), templateArgs);

                console.println("\n%s files:", concept.getName());
                conceptFiles.forEach(targetFile -> renderTargetFile(targetFile, targetDirPath, templateArgs));
            });

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
            final String path = targetDirPath + "/" + targetFile.getPath();
            final String contents = templateRenderer.renderTemplate(
                targetFile.getTemplateFile().get(),
                targetFile.getTemplateName(),
                args);

            fileSystem.createFile(path, contents);
        }
    }

    private Map<String, Object> getTargetProperties(final Target target)
    {
        final Map<String, Object> properties = new HashMap<>();

        modelRepository.getProperties(target)
                       .forEach(property -> properties.put(property.getName(), property.getValue()));

        return properties;
    }
}
