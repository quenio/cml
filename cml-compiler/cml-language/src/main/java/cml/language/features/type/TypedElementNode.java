package cml.language.features.type;

import cml.language.foundation.elements.NamedElementNode;
import cml.language.foundation.elements.SourceLocation;
import org.jetbrains.annotations.Nullable;

public class TypedElementNode extends NamedElementNode
{
    private @Nullable TypeNode typeNode;

    public TypedElementNode(SourceLocation location, String name)
    {
        super(location, name);
    }

    // DMR: 9.2.2b
    public void setTypeNode(@Nullable TypeNode typeNode)
    {
        this.typeNode = typeNode;
    }
}
