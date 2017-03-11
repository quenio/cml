package cml.language.features;

import cml.language.foundation.ModelElement;
import cml.language.foundation.NamedElement;
import cml.language.foundation.PropertyList;
import cml.language.foundation.Scope;

import java.util.List;
import java.util.Optional;

public interface Target extends NamedElement, PropertyList
{
    static Target create(String name)
    {
        final ModelElement modelElement = ModelElement.create();
        final NamedElement namedElement = NamedElement.create(modelElement, name);
        final Scope scope = Scope.create(modelElement);
        final PropertyList propertyList = PropertyList.create(scope);

        return new TargetImpl(modelElement, namedElement, propertyList);
    }
}

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

