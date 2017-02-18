package cml.language.foundation;

import org.jetbrains.annotations.NotNull;

public abstract class ModelElement
{
    @NotNull
    private final SourceLocation location;

    public ModelElement(@NotNull SourceLocation location)
    {
        this.location = location;
    }

    Scope createRootScope()
    {
        return new Scope(this);
    }

    Scope createScope(@NotNull Scope parent)
    {
        return new Scope(parent, this);
    }
}
