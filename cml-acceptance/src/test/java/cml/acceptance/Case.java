package cml.acceptance;

class Case
{
    private final String name;
    private final String targetType;
    private final String javaClient;

    Case(String name, String targetType, String javaClient)
    {
        this.name = name;
        this.targetType = targetType;
        this.javaClient = javaClient;
    }

    String getName()
    {
        return name;
    }

    String getTargetType()
    {
        return targetType;
    }

    String getJavaClient()
    {
        return javaClient;
    }
}
