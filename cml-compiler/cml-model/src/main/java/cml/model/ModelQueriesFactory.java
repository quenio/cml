package cml.model;

public class ModelQueriesFactory
{
    public static ModelQueries createModelQueries()
    {
        return new PlainModelQueries();
    }
}
