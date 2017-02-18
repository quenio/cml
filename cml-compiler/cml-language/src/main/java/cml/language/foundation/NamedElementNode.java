package cml.language.foundation;

public class NamedElementNode extends Node
{
    private String name;

    public NamedElementNode(SourceLocation location, String name)
    {
        super(location);

        this.name = name;
    }
}
