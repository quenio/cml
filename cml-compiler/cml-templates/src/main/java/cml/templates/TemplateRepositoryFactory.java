package cml.templates;

public class TemplateRepositoryFactory
{
    public static TemplateRepository createTemplateRepository()
    {
        return new PlainTemplateRepository();
    }
}
