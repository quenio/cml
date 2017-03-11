package cml.acceptance;

public class Case
{
    private final String name;
    private final String javaClient;

    public Case(String name, String javaClient)
    {
        this.name = name;
        this.javaClient = javaClient;
    }

    public String getName()
    {
        return name;
    }

    public String getJavaClient()
    {
        return javaClient;
    }
}
