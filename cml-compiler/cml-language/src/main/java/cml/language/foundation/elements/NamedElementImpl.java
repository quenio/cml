package cml.language.foundation.elements;

import java.util.Optional;

public class NamedElementImpl implements NamedElement
{
    private final ModelElement modelElement;
    private final String name;

    public NamedElementImpl(ModelElement modelElement, String name)
    {
        this.modelElement = modelElement;
        this.name = name;
    }

    @Override
    public Optional<Scope> getParentScope()
    {
        return modelElement.getParentScope();
    }

    @Override
    public String getName()
    {
        return name;
    }
}
