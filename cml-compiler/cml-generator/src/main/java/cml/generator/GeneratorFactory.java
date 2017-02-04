package cml.generator;

public class GeneratorFactory
{
    public static Generator createGenerator(final TargetRepository targetRepository)
    {
        return new PlainGenerator(targetRepository);
    }
}
