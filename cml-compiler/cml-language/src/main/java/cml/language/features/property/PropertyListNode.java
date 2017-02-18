package cml.language.features.property;

import cml.language.foundation.elements.Node;
import cml.language.foundation.elements.SourceLocation;

import java.util.ArrayList;
import java.util.List;

public class PropertyListNode extends Node
{
    private final List<PropertyNode> propertyNodes = new ArrayList<>();

    PropertyListNode(SourceLocation location)
    {
        super(location);
    }
}
