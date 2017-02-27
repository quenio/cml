package cml.language.features.type;

import cml.language.foundation.elements.NamedElement;
import cml.language.foundation.elements.Scope;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class TypedElementImpl implements TypedElement
{
    private final NamedElement namedElement;
    private boolean typeRequired;
    private boolean typeAllowed;
    private @Nullable Type type;
    
    public TypedElementImpl(NamedElement namedElement)
    {
        this.namedElement = namedElement;
    }

    @Override
    public String getName()
    {
        return namedElement.getName();
    }

    @Override
    public Optional<Scope> getParentScope()
    {
        return namedElement.getParentScope();
    }

    @Override
    public boolean isTypeRequired()
    {
        return typeRequired;
    }

    @Override
    public void setTypeRequired(boolean typeRequired)
    {
        this.typeRequired = typeRequired;
    }

    @Override
    public boolean isTypeAllowed()
    {
        return typeAllowed;
    }

    @Override
    public void setTypeAllowed(boolean typeAllowed)
    {
        this.typeAllowed = typeAllowed;
    }

    @Override
    public Optional<Type> getType()
    {
        return Optional.ofNullable(type);
    }

    @Override
    public void setType(@Nullable Type type)
    {
        this.type = type;
    }

}
