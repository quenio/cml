package cml.language.foundation;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface PropertyList extends Scope
{
    default List<Property> getProperties()
    {
        return getElements().stream()
                            .filter(e -> e instanceof Property)
                            .map(e -> (Property)e)
                            .collect(toList());
    }
}

