package cml.model;

public class PlainModelBuilder implements ModelBuilder
{
    @Override
    public Model createModel(final String content)
    {
        return new Model(content);
    }
}
