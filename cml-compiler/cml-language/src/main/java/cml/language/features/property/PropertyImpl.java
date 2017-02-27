package cml.language.features.property;

import cml.language.features.type.Type;
import cml.language.foundation.elements.Scope;
import cml.language.features.type.TypedElement;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

class PropertyImpl implements Property
{
    private final TypedElement typedElement;
    private final String value;

    PropertyImpl(TypedElement typedElement, String value)
    {
        this.typedElement = typedElement;
        this.value = value;
    }

    @Override
    public Optional<Scope> getParentScope()
    {
        return typedElement.getParentScope();
    }

    @Override
    public boolean isTypeRequired()
    {
        return typedElement.isTypeRequired();
    }

    @Override
    public void setTypeRequired(boolean typeRequired)
    {
        typedElement.setTypeRequired(typeRequired);
    }

    @Override
    public boolean isTypeAllowed()
    {
        return typedElement.isTypeAllowed();
    }

    @Override
    public void setTypeAllowed(boolean typeAllowed)
    {
        typedElement.setTypeAllowed(true);
    }

    @Override
    public Optional<Type> getType()
    {
        return typedElement.getType();
    }

    @Override
    public void setType(@Nullable Type type)
    {
        typedElement.setType(type);
    }

    @Override
    public String getName()
    {
        return typedElement.getName();
    }

    @Override
    public String getValue()
    {
        return value;
    }
}
