package cml.language.features.type;

import cml.language.foundation.elements.NamedElementNode;
import cml.language.foundation.elements.SourceLocation;
import org.jetbrains.annotations.Nullable;

public class TypeNode extends NamedElementNode
{
    private @Nullable Type type;

    public TypeNode(SourceLocation location, String name)
    {
        super(location, name);
    }

    // DMR: 9.2.2b
    public void setType(@Nullable Type type)
    {
        this.type = type;
    }
}