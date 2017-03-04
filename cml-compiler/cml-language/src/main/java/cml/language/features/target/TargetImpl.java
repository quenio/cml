package cml.language.features.target;

import cml.language.features.property.PropertySet;
import cml.language.foundation.elements.ModelElement;
import cml.language.foundation.elements.NamedElement;
import cml.language.foundation.elements.Scope;

import java.util.Optional;
import java.util.Set;

class TargetImpl implements Target
{
    private final ModelElement modelElement;
    private final NamedElement namedElement;
    private final PropertySet propertySet;

    TargetImpl(ModelElement modelElement, NamedElement namedElement, PropertySet propertySet)
    {
        this.modelElement = modelElement;
        this.namedElement = namedElement;
        this.propertySet = propertySet;
    }

    @Override
    public Optional<Scope> getParentScope()
    {
        return modelElement.getParentScope();
    }

    @Override
    public String getName()
    {
        return namedElement.getName();
    }

    @Override
    public Set<ModelElement> getElements()
    {
        return propertySet.getElements();
    }

    @Override
    public void addElement(ModelElement element)
    {
        propertySet.addElement(element);
    }
}
