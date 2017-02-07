package cml.model;

public class Property
{
    // Attributes:
    private final String name;
    private final String value;

    public Property(final String name, final String value)
    {
        this.name = name;
        this.value = value;
    }

    // Attributes:


    public String getName()
    {
        return name;
    }

    public String getValue()
    {
        return value;
    }
}
