package cml.language.foundation.elements;

public abstract class ModelElement
{
    private final SourceLocation location;

    ModelElement(SourceLocation location)
    {
        this.location = location;
    }

    Scope createRootScope()
    {
        return new Scope(this);
    }

    Scope createScope(Scope parent)
    {
        return new Scope(parent, this);
    }
}
