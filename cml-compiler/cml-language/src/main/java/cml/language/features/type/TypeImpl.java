package cml.language.features.type;

import cml.language.foundation.elements.ModelElement;
import cml.language.foundation.elements.NamedElement;
import cml.language.foundation.elements.Scope;

import java.util.Optional;

class TypeImpl implements Type
{
    private final ModelElement modelElement;
    private final NamedElement namedElement;

    TypeImpl(ModelElement modelElement, NamedElement namedElement)
    {
        this.modelElement = modelElement;
        this.namedElement = namedElement;
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
}
