package cml.generator;

import cml.io.Console;
import cml.model.ModelQueries;
import cml.templates.TemplateRenderer;
import cml.templates.TemplateRepository;

import static cml.model.ModelQueriesFactory.createModelQueries;
import static cml.templates.TemplateRepositoryFactory.createTemplateRepository;

public class GeneratorFactory
{
    public static Generator createGenerator(final Console console)
    {
        final ModelQueries modelQueries = createModelQueries();
        final TemplateRepository templateRepository = createTemplateRepository();
        final TemplateRenderer templateRenderer = null;
        final TargetFileRepository targetFileRepository = new PlainTargetFileRepository(templateRepository, templateRenderer);
        return new PlainGenerator(console, modelQueries, targetFileRepository);
    }
}
