package cml.language.features.property;

import cml.language.foundation.elements.ModelElement;
import cml.language.foundation.elements.Scope;

import java.util.List;
import java.util.Optional;

public class PropertyListImpl implements PropertyList
{
    private final Scope scope;

    public PropertyListImpl(Scope scope)
    {
        this.scope = scope;
    }

    @Override
    public Optional<Scope> getParentScope()
    {
        return scope.getParentScope();
    }

    @Override
    public List<ModelElement> getElements()
    {
        return scope.getElements();
    }

    @Override
    public void addElement(ModelElement element)
    {
        scope.addElement(element);
    }
}
