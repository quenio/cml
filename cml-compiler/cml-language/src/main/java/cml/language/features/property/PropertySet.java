package cml.language.features.property;

import cml.language.foundation.elements.Scope;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public interface PropertySet extends Scope
{
    default Set<Property> getProperties()
    {
        return getElements().stream()
                            .filter(e -> e instanceof PropertyImpl)
                            .map(e -> (Property)e)
                            .collect(toSet());
    }
}
