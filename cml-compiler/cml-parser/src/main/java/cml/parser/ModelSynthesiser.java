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
    public void exitConcept(final CMLParser.ConceptContext ctx)
    {
        final String name = ctx.NAME().getText();
        modelBuilder.addConcept(name);
    }
}
