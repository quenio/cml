package cml.language.features.property;

import cml.language.foundation.elements.Scope;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface PropertyList extends Scope
{
    default List<Property> getProperties()
    {
        return getElements().stream()
                            .filter(e -> e instanceof PropertyImpl)
                            .map(e -> (Property)e)
                            .collect(toList());
    }
}
