package cml.generator;

import cml.io.Console;
import cml.io.FileSystem;
import cml.model.ModelRepository;
import cml.templates.TemplateRenderer;
import cml.templates.TemplateRepository;

import static cml.model.ModelRepositoryFactory.createModelQueries;
import static cml.templates.TemplateRendererFactory.createTemplateRenderer;
import static cml.templates.TemplateRepositoryFactory.createTemplateRepository;

public class GeneratorFactory
{
    public static Generator createGenerator(final Console console, final FileSystem fileSystem)
    {
        final ModelRepository modelRepository = createModelQueries();
        final TemplateRepository templateRepository = createTemplateRepository();
        final TemplateRenderer templateRenderer = createTemplateRenderer();
        final TargetFileRepository targetFileRepository = new PlainTargetFileRepository(templateRepository, templateRenderer);
        return new PlainGenerator(console, fileSystem, modelRepository, targetFileRepository, templateRenderer);
    }
}
