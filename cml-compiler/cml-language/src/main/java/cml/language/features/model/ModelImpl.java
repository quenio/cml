package cml.language.features.model;

import cml.language.foundation.elements.ModelElement;
import cml.language.foundation.elements.Scope;

import java.util.List;
import java.util.Optional;

class ModelImpl implements Model
{
    private final ModelElement modelElement;
    private final Scope scope;

    ModelImpl(ModelElement modelElement, Scope scope)
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
