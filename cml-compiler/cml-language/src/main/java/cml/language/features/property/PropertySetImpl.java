package cml.language.features.property;

import cml.language.foundation.elements.ModelElement;
import cml.language.foundation.elements.Scope;

import java.util.Optional;
import java.util.Set;

public class PropertySetImpl implements PropertySet
{
    private final Scope scope;

    public PropertySetImpl(Scope scope)
    {
        this.scope = scope;
    }

    @Override
    public Optional<Scope> getParentScope()
    {
        return scope.getParentScope();
    }

    @Override
    public Set<ModelElement> getElements()
    {
        return scope.getElements();
    }

    @Override
    public void addElement(ModelElement element)
    {
        scope.addElement(element);
    }
}
