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
        final ModelElement modelElement = ModelElement.create();
        final NamedElement namedElement = NamedElement.create(modelElement, name);
        return new TypeImpl(modelElement, namedElement, cardinality);
    }
}

class TypeImpl implements Type
{
    private final ModelElement modelElement;
    private final NamedElement namedElement;
    private final @Nullable String cardinality;

    TypeImpl(ModelElement modelElement, NamedElement namedElement, @Nullable String cardinality)
    {
        this.modelElement = modelElement;
        this.namedElement = namedElement;
        this.cardinality = cardinality;
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

    @Override
    public Optional<String> getCardinality()
    {
        return Optional.ofNullable(cardinality);
    }
}

