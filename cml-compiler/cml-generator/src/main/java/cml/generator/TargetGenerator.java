package cml.generator;

import cml.io.Console;
import cml.language.features.Target;
import cml.language.grammar.CMLBaseListener;
import cml.language.grammar.CMLParser.ConceptNodeContext;
import cml.language.grammar.CMLParser.ModelNodeContext;

import java.util.HashMap;
import java.util.Map;

class TargetGenerator extends CMLBaseListener
{
    private static final String MODEL = "model";
    private static final String CONCEPT = "concept";

    private final Console console;
    private final TargetFileRenderer targetFileRenderer;
    private final Target target;
    private final String targetDirPath;
    private final Map<String, Object> baseTemplateArgs;
    
    TargetGenerator(
        Console console,
        TargetFileRenderer targetFileRenderer,
        Target target,
        String targetDirPath)
    {
        this.console = console;
        this.targetFileRenderer = targetFileRenderer;
        this.target = target;
        this.targetDirPath = targetDirPath;
        this.baseTemplateArgs = new HashMap<>();

        baseTemplateArgs.put(target.getName(), getTargetProperties(target));
    }

    @Override
    public void enterModelNode(ModelNodeContext ctx)
    {
        console.println("%s files:", MODEL);

        generateTargetFiles(MODEL, ctx.model);
    }

    @Override
    public void enterConceptNode(ConceptNodeContext ctx)
    {
        console.println("\n%s files:", ctx.concept.getName());
        
        generateTargetFiles(CONCEPT, ctx.concept);
    }

    private void generateTargetFiles(String elementType, Object modelElement)
    {
        final Map<String, Object> templateArgs = new HashMap<>(baseTemplateArgs);
        templateArgs.put(elementType, modelElement);

        targetFileRenderer.renderTargetFiles(target, targetDirPath, elementType, templateArgs);
    }

    private static Map<String, Object> getTargetProperties(final Target target)
    {
        final Map<String, Object> properties = new HashMap<>();

        target.getProperties().forEach(property -> properties.put(property.getName(), property.getValue()));

        return properties;
    }
}
