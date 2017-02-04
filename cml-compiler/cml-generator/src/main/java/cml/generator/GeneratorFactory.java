package cml.generator;

import cml.templates.TemplateRepository;

import static cml.templates.TemplateRepositoryFactory.createTemplateRepository;

public class GeneratorFactory
{
    public static Generator createGenerator()
    {
        final TemplateRepository templateRepository = createTemplateRepository();
        return new PlainGenerator(templateRepository);
    }
}
