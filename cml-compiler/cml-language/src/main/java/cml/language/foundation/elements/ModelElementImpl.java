package cml.language.foundation.elements;

import java.util.Optional;

public class ModelElementImpl implements ModelElement
{
    @Override
    public Optional<Scope> getParentScope()
    {
        return scopeElement.getParentScope(this);
    }

    private static ScopeElement scopeElement;

    static void setScopeElement(ScopeElement association)
    {
        assert scopeElement == null;

        scopeElement = association;
    }

    static
    {
        ScopeElement.init(ModelElementImpl.class);
    }
}
