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
        return new TargetImpl(name);
    }
}

class TargetImpl implements Target
{
    private final ModelElement modelElement;
    private final NamedElement namedElement;
    private final Scope scope;

    TargetImpl(String name)
    {
        this.modelElement = ModelElement.create(this);
        this.namedElement = NamedElement.create(modelElement, name);
        this.scope = Scope.create(this, modelElement);
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
        return scope.getElements();
    }

    @Override
    public void addElement(ModelElement element)
    {
        scope.addElement(element);
    }
}

