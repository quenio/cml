package cml.language.interfaces.builder;

import cml.language.features.model.ModelNode;
import cml.language.foundation.elements.SourceLocation;

public interface ModelBuilder
{
    void startBuilding();
    ModelNode finishingBuilding();

    @BeforeRule("ModelNode")
    void createModelNode(SourceLocation location);

    @BeforeRule("ModelElementNode")
    void createModelElementNode(SourceLocation location);

    @AfterRule("ModelElementNode")
    void includeModelElementNode(SourceLocation location);

    @AfterRule("ConceptNode")
    void includeConceptNode(SourceLocation location, String name);

    @AfterRule("TargetNode")
    void includeTargetNode(SourceLocation location, String name);

    @BeforeRule("PropertyListNode")
    void createPropertyListNode(SourceLocation location);

    @AfterRule("PropertyNode")
    void includePropertyNode(SourceLocation location, String name, String string);

    @AfterRule("TypeNode")
    void includeTypeNode(SourceLocation location, String name);
}
