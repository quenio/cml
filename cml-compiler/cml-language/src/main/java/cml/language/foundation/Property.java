package cml.language.foundation;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface Property extends TypedElement
{
    Optional<String> getValue();

    static Property create(String name, @Nullable String value, @Nullable Type type)
    {
        return new PropertyImpl(name, value, type);
    }
}

class PropertyImpl implements Property
{
    private final TypedElement typedElement;
    private final @Nullable String value;

    PropertyImpl(String name, @Nullable String value, @Nullable Type type)
    {
        final ModelElement modelElement = ModelElement.create(this);
        final NamedElement namedElement = NamedElement.create(modelElement, name);

        this.typedElement = TypedElement.create(namedElement, type);
        this.value = value;
    }

    @Override
    public Optional<Scope> getParentScope()
    {
        return typedElement.getParentScope();
    }

    @Override
    public Optional<Type> getType()
    {
        return typedElement.getType();
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
    public String getName()
    {
        return typedElement.getName();
    }

    @Override
    public Optional<String> getValue()
    {
        return Optional.ofNullable(value);
    }
}

