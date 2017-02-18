package cml.language.features.concept;

import cml.language.features.property.PropertyListNode;
import cml.language.foundation.elements.NamedElementNode;
import cml.language.foundation.elements.SourceLocation;
import org.jetbrains.annotations.Nullable;

public class ConceptNode extends NamedElementNode
{
    private @Nullable PropertyListNode propertyListNode;

    private @Nullable Concept concept;

    public ConceptNode(SourceLocation location, String name)
    {
        super(location, name);
    }

    // DMR: 9.2.2b
    public void setPropertyListNode(PropertyListNode propertyListNode)
    {
        this.propertyListNode = propertyListNode;
    }
}
