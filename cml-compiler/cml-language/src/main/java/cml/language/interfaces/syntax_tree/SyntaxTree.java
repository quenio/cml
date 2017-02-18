package cml.language.interfaces.syntax_tree;

import cml.language.features.concept.ConceptNode;
import cml.language.features.model.ModelElementNode;
import cml.language.features.model.ModelNode;
import cml.language.features.property.PropertyListNode;
import cml.language.features.target.TargetNode;
import cml.language.features.type.TypeNode;
import cml.language.foundation.elements.SourceLocation;
import org.jetbrains.annotations.Nullable;

// DMR: 9.4.1a
class SyntaxTree implements SyntaxTreeBuilder
{
    private @Nullable ModelNode modelNode;

    private @Nullable ModelElementNode currentModelElementNode;
    private @Nullable PropertyListNode currentPropertyListNode;
    private @Nullable TypeNode currentTypeNode;

    // DMR: 9.4.1a
    @Override
    public void createModelNode(SourceLocation location)
    {
        modelNode = new ModelNode(location);
    }

    // DMR: 9.4.1a
    @Override
    public void createModelElementNode(SourceLocation location)
    {
        currentModelElementNode = new ModelElementNode(location);

        currentPropertyListNode = null;
    }

    // DMR: 9.4.1a
    @Override
    public void includeModelElementNode(SourceLocation location)
    {
        assert modelNode != null : "require syntaxTree.modelNode";
        assert currentModelElementNode != null : "require syntaxTree.currentModelElementNode";

        modelNode.addModelElementNode(currentModelElementNode);
    }

    // DMR: 9.4.1a
    @Override
    public void includeConceptNode(SourceLocation location, String name)
    {
        assert currentModelElementNode != null : "require syntaxTree.currentModelElementNode";

        final ConceptNode conceptNode = new ConceptNode(location, name);

        conceptNode.setPropertyListNode(currentPropertyListNode);

        currentModelElementNode.setConceptNode(conceptNode);
    }

    // DMR: 9.4.1a
    @Override
    public void includeTargetNode(SourceLocation location, String name)
    {
        assert currentModelElementNode != null : "require syntaxTree.currentModelElementNode";
        assert currentPropertyListNode != null : "require syntaxTree.currentPropertyListNode";

        final TargetNode targetNode = new TargetNode(location, name, currentPropertyListNode);

        currentModelElementNode.setTargetNode(targetNode);
    }

    // DMR: 9.4.1a
    @Override
    public void createPropertyList(SourceLocation location)
    {

    }

    // DMR: 9.4.1a
    @Override
    public void includeProperty(SourceLocation location, String name, String string)
    {

    }

    // DMR: 9.4.1a
    @Override
    public void includeType(SourceLocation location, String name)
    {

    }
}
