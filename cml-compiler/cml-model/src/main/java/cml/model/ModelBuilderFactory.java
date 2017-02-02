package cml.model;

public class ModelBuilderFactory
{
    public static ModelBuilder createModelBuilder()
    {
        return new PlainModelBuilder();
    }
}
