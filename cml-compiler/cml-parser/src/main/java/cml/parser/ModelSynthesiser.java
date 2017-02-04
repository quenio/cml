package cml.parser;

import cml.antlr.CMLBaseListener;
import cml.antlr.CMLParser;
import cml.model.ModelBuilder;

class ModelSynthesiser extends CMLBaseListener
{
    private final ModelBuilder modelBuilder;

    ModelSynthesiser(final ModelBuilder modelBuilder)
    {
        this.modelBuilder = modelBuilder;
    }

    @Override
    public void enterStart(final CMLParser.StartContext ctx)
    {
        modelBuilder.newModel();
    }

    @Override
    public void enterConcept(final CMLParser.ConceptContext ctx)
    {
        final String name = ctx.NAME().getText();
        
        modelBuilder.addConcept(name);
    }

    @Override
    public void enterTarget(final CMLParser.TargetContext ctx)
    {
        final String targetType = ctx.NAME().getText();

        modelBuilder.addTarget(targetType);
    }

    @Override
    public void enterProperty(final CMLParser.PropertyContext ctx)
    {
        final String propertyName = ctx.NAME().getText();
        final String propertyValue = ctx.STRING().getText();

        modelBuilder.addProperty(propertyName, propertyValue);
    }

}
