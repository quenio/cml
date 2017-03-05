package cml.language.grammar;

import cml.language.features.Concept;
import cml.language.features.Model;
import cml.language.foundation.Property;
import cml.language.features.Target;
import cml.language.foundation.Type;
import cml.language.grammar.CMLParser.*;

public class ModelSynthesizer extends CMLBaseListener
{
    private static final String QUOTE = "\"";

    @Override
    public void exitModelNode(ModelNodeContext ctx)
    {
        ctx.model = Model.create();

        if (ctx.modelElementNode() != null)
        {
            ctx.modelElementNode()
               .stream()
               .filter(node -> node.conceptNode() != null)
               .forEach(node -> ctx.model.addElement(node.conceptNode().concept));

            ctx.modelElementNode()
               .stream()
               .filter(node -> node.targetNode() != null)
               .forEach(node -> ctx.model.addElement(node.targetNode().target));
        }
    }

    @Override
    public void exitConceptNode(ConceptNodeContext ctx)
    {
        final String name = ctx.NAME().getText();

        ctx.concept = Concept.create(name);

        if (ctx.propertyListNode() != null)
        {
            ctx.propertyListNode()
               .propertyNode()
               .forEach(node -> ctx.concept.addElement(node.property));
        }
    }

    @Override
    public void exitTargetNode(TargetNodeContext ctx)
    {
        final String name = ctx.NAME().getText();

        ctx.target = Target.create(name);

        if (ctx.propertyListNode() != null)
        {
            ctx.propertyListNode()
               .propertyNode()
               .forEach(node -> ctx.target.addElement(node.property));
        }
    }

    @Override
    public void exitPropertyNode(PropertyNodeContext ctx)
    {
        final String name = ctx.NAME().getText();
        final String value = unwrap(ctx.STRING().getText());

        ctx.property = Property.create(name, value);
    }

    @Override
    public void exitTypeNode(TypeNodeContext ctx)
    {
        final String name = ctx.NAME().getText();

        ctx.type = Type.create(name);
    }


    private static String unwrap(String text)
    {
        if (text.startsWith(QUOTE))
        {
            text = text.substring(1);
        }

        if (text.endsWith(QUOTE))
        {
            text = text.substring(0, text.length() - 1);
        }

        return text;
    }
}
