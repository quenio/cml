package cml.language.features.type;

import cml.language.foundation.elements.NamedElement;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface TypedElement extends NamedElement
{
    boolean isTypeRequired();
    void setTypeRequired(boolean typeRequired);

    boolean isTypeAllowed();
    void setTypeAllowed(boolean typeAllowed);

    Optional<Type> getType();
    void setType(@Nullable Type type);
}
