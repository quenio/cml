package cml.language.foundation;

import java.util.Optional;

public interface ModelElement
{
    Optional<Scope> getParentScope();

    static ModelElement create(ModelElement self)
    {
        return new ModelElementImpl(self);
    }
}

class ModelElementImpl implements ModelElement
{
    private final ModelElement self;

    ModelElementImpl(ModelElement self)
    {
        this.self = self;
    }

    @Override
    public Optional<Scope> getParentScope()
    {
        return scopeElement.getParentScope(self);
    }

    private static ScopeElement scopeElement;

    static void setScopeElement(ScopeElement association)
    {
        assert scopeElement == null;

        scopeElement = association;
    }

    static
    {
        ScopeElement.init(ModelElementImpl.class);
    }
}

