package cml.language.foundation;

import java.util.Set;

public class NamedElement extends ModelElement
{
    private final String name;
    private final Scope scope;

    public NamedElement(SourceLocation location, String name, Scope scope)
    {
        super(location);
        this.name = name;
        this.scope = scope;
    }

    String getName()
    {
        return name;
    }

    Set<NamedElement> findLocalConflicts()
    {
        return scope.findLocalConflicts(this);
    }
}
