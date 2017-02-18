package cml.language.features.model;

import cml.language.features.concept.ConceptNode;
import cml.language.features.target.TargetNode;
import cml.language.foundation.elements.Node;
import cml.language.foundation.elements.SourceLocation;
import org.jetbrains.annotations.Nullable;

public class ModelElementNode extends Node
{
    private @Nullable ConceptNode conceptNode;
    private @Nullable TargetNode targetNode;

    public ModelElementNode(SourceLocation location)
    {
        super(location);
    }

    public void setConceptNode(@Nullable ConceptNode conceptNode)
    {
        this.conceptNode = conceptNode;
    }

    public void setTargetNode(@Nullable TargetNode targetNode)
    {
        this.targetNode = targetNode;
    }
}
