package cml.language.foundation;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface TypedElement extends NamedElement
{
    Optional<Type> getType();

    boolean isTypeRequired();
    void setTypeRequired(boolean typeRequired);

    boolean isTypeAllowed();
    void setTypeAllowed(boolean typeAllowed);

    static TypedElement create(NamedElement namedElement, @Nullable Type type)
    {
        return new TypedElementImpl(namedElement, type);
    }
}

class TypedElementImpl implements TypedElement
{
    private final NamedElement namedElement;
    private final @Nullable Type type;
    private boolean typeRequired;
    private boolean typeAllowed;

    TypedElementImpl(NamedElement namedElement, @Nullable Type type)
    {
        this.namedElement = namedElement;
        this.type = type;
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
    public Optional<Type> getType()
    {
        return Optional.ofNullable(type);
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
}

