package cml.language.features.model;

import cml.language.foundation.elements.Node;
import cml.language.foundation.elements.SourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ModelNode extends Node
{
    private final List<ModelElementNode> modelElementNodes = new ArrayList<>();

    @Nullable
    private Model model;

    public ModelNode(SourceLocation location)
    {
        super(location);
    }

    // DMR: 9.2.2b
    public boolean addModelElementNode(ModelElementNode modelElementNode)
    {
        return modelElementNodes.add(modelElementNode);
    }
}
