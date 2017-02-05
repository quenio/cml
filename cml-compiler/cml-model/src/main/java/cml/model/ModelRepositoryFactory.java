package cml.model;

public class ModelRepositoryFactory
{
    public static ModelRepository createModelQueries()
    {
        return new PlainModelRepository();
    }
}
