package cml.language.features.property;

import cml.language.features.type.TypedElementNode;
import cml.language.foundation.elements.SourceLocation;
import org.jetbrains.annotations.Nullable;

public class PropertyNode extends TypedElementNode
{
    private final String string;

    private @Nullable Property property;

    public PropertyNode(SourceLocation location, String name, String string)
    {
        super(location, name);

        this.string = string;
    }
}
