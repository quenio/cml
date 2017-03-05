package cml.language.foundation;

import java.util.Optional;

public interface Type extends NamedElement
{
    static Type create(String name)
    {
        final ModelElement modelElement = ModelElement.create();
        final NamedElement namedElement = NamedElement.create(modelElement, name);
        return new TypeImpl(modelElement, namedElement);
    }
}

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

