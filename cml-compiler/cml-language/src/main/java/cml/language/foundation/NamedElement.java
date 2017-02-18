package cml.language.foundation;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class NamedElement extends ModelElement
{
    @NotNull
    private final String name;

    @NotNull
    private final Scope scope;

    public NamedElement(@NotNull SourceLocation location, @NotNull String name, @NotNull Scope scope)
    {
        super(location);
        this.name = name;
        this.scope = scope;
    }

    @NotNull
    public String getName()
    {
        return name;
    }

    public Set<NamedElement> findLocalConflicts()
    {
        return scope.findLocalConflicts(this);
    }
}
