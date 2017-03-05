package cml.language.foundation;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface Property extends TypedElement
{
    String getValue();

    static Property create(String name, String value)
    {
        final ModelElement modelElement = ModelElement.create();
        final NamedElement namedElement = NamedElement.create(modelElement, name);
        final TypedElement typedElement = TypedElement.create(namedElement);
        return new PropertyImpl(typedElement, value);
    }
}

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

