package cml.model;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

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

    private final List<Property> properties = new ArrayList<>();

    List<Property> getProperties()
    {
        return unmodifiableList(properties);
    }

    void addProperty(final Property property)
    {
        properties.add(property);
    }
}
