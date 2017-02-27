package cml.language.features.model;

import cml.language.foundation.elements.ModelElement;
import cml.language.foundation.elements.Scope;

import java.util.Optional;
import java.util.Set;

public class ModelImpl implements Model
{
    private final ModelElement modelElement;
    private final Scope scope;

    public ModelImpl(ModelElement modelElement, Scope scope)
    {
        this.modelElement = modelElement;
        this.scope = scope;
    }

    @Override
    public Optional<Scope> getParentScope()
    {
        return modelElement.getParentScope();
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
