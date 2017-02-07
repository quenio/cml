package cml.model;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

public class Target
{
    Target(final String targetType)
    {
        this.targetType = targetType;
    }

    // Attributes:

    private final String targetType;

    public String getTargetType()
    {
        return targetType;
    }

    // Associations:

    private final Set<Property> properties = new HashSet<>();

    Set<Property> getProperties()
    {
        return unmodifiableSet(properties);
    }

    void addProperty(final Property property)
    {
        properties.add(property);
    }
}
