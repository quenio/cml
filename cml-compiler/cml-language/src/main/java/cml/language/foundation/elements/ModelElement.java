package cml.language.foundation.elements;

import java.util.Optional;

public interface ModelElement
{
    Optional<Scope> getParentScope();
}
