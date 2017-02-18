package cml.language.interfaces.syntax_tree;

import cml.language.foundation.elements.SourceLocation;

public interface SyntaxTreeBuilder
{
    @BeforeRule("model")
    void createModelNode(SourceLocation location);

    @BeforeRule("modelElement")
    void createModelElementNode(SourceLocation location);

    @AfterRule("modelElement")
    void includeModelElementNode(SourceLocation location);

    @AfterRule("concept")
    void includeConceptNode(SourceLocation location, String name);

    @AfterRule("target")
    void includeTargetNode(SourceLocation location, String name);

    @BeforeRule("propertyList")
    void createPropertyList(SourceLocation location);

    @AfterRule("property")
    void includeProperty(SourceLocation location, String name, String string);

    @AfterRule("type")
    void includeType(SourceLocation location, String name);
}
