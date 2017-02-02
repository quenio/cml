package cml.generator;

public class GeneratorFactory
{
    public static Generator createGenerator()
    {
        final TemplateDirectory templateDirectory = new PlainTemplateDirectory();
        return new PlainGenerator(templateDirectory);
    }
}
