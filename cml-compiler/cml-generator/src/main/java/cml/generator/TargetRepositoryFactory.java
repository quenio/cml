package cml.generator;

import cml.templates.TemplateRepository;

import static cml.templates.TemplateRepositoryFactory.createTemplateRepository;

public class TargetRepositoryFactory
{
    public static TargetRepository createTargetRepository()
    {
        final TemplateRepository templateRepository = createTemplateRepository();
        return new PlainTargetRepository(templateRepository);
    }
}
