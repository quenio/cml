package cml.language.foundation.elements;

import java.util.Optional;
import java.util.Set;

public class ScopeImpl implements Scope
{
    private final ModelElement modelElement;

    public ScopeImpl(ModelElement modelElement)
    {
        this.modelElement = modelElement;
    }

    @Override
    public Optional<Scope> getParentScope()
    {
        return modelElement.getParentScope();
    }

    @Override
    public Set<ModelElement> getElements()
    {
        return scopeElement.getElements(this);
    }

    @Override
    public void addElement(ModelElement element)
    {
        scopeElement.link(this, element);
    }

    private static ScopeElement scopeElement;

    static void setScopeElement(ScopeElement association)
    {
        assert scopeElement == null;

        scopeElement = association;
    }
}
