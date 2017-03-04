package cml.language.features.target;

import cml.language.features.property.PropertyList;
import cml.language.foundation.elements.ModelElement;
import cml.language.foundation.elements.NamedElement;
import cml.language.foundation.elements.Scope;

import java.util.List;
import java.util.Optional;

class TargetImpl implements Target
{
    private final ModelElement modelElement;
    private final NamedElement namedElement;
    private final PropertyList propertyList;

    TargetImpl(ModelElement modelElement, NamedElement namedElement, PropertyList propertyList)
    {
        this.modelElement = modelElement;
        this.namedElement = namedElement;
        this.propertyList = propertyList;
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
    public List<ModelElement> getElements()
    {
        return propertyList.getElements();
    }

    @Override
    public void addElement(ModelElement element)
    {
        propertyList.addElement(element);
    }
}
