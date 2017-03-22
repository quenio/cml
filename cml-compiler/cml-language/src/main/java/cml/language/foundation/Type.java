package cml.language.foundation;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface Type extends NamedElement
{
    String REQUIRED = "required";
    String OPTIONAL = "optional";
    String SET = "set";

    Optional<String> getCardinality();

    default String getKind()
    {
        if (getCardinality().isPresent())
        {
            final String cardinality = getCardinality().get();

            if (cardinality.matches("\\?"))
            {
                return OPTIONAL;
            }
            else if (cardinality.matches("(\\*|\\+)"))
            {
                return SET;
            }
        }

        return REQUIRED;
    }

    static Type create(String name, @Nullable String cardinality)
    {
        return new TypeImpl(name, cardinality);
    }
}

class TypeImpl implements Type
{
    private final NamedElement namedElement;
    private final @Nullable String cardinality;

    TypeImpl(String name, @Nullable String cardinality)
    {
        this.namedElement = NamedElement.create(this, name);
        this.cardinality = cardinality;
    }

    @Override
    public Optional<Scope> getParentScope()
    {
        return namedElement.getParentScope();
    }

    @Override
    public String getName()
    {
        return namedElement.getName();
    }

    @Override
    public Optional<String> getCardinality()
    {
        return Optional.ofNullable(cardinality);
    }
}

