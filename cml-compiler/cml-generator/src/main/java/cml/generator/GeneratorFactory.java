package cml.generator;

public class GeneratorFactory
{
    public static Generator createGenerator()
    {
        final TemplateRepository templateRepository = new PlainTemplateRepository();
        return new PlainGenerator(templateRepository);
    }
}
