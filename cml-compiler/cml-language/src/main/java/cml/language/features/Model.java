package cml.language.features;

import cml.language.foundation.ModelElement;
import cml.language.foundation.Scope;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public interface Model extends Scope
{
    default List<Concept> getConcepts()
    {
        return getElements().stream()
                            .filter(e -> e instanceof Concept)
                            .map(e -> (Concept)e)
                            .collect(toList());
    }

    default List<Target> getTargets()
    {
        return getElements().stream()
                            .filter(e -> e instanceof Target)
                            .map(e -> (Target)e)
                            .collect(toList());
    }

    default Optional<Target> getTarget(String name)
    {
        return getTargets().stream().filter(t -> t.getName().equals(name)).findFirst();
    }

    static Model create()
    {
        final ModelElement modelElement = ModelElement.create();
        final Scope scope = Scope.create(modelElement);
        
        return new ModelImpl(modelElement, scope);
    }
}

class ModelImpl implements Model
{
    private final ModelElement modelElement;
    private final Scope scope;

    ModelImpl(ModelElement modelElement, Scope scope)
    {
        this.modelElement = modelElement;
        this.scope = scope;
    }

    @Override
    public Optional<Scope> getParentScope()
    {
        return modelElement.getParentScope();
    }

    @Override
    public List<ModelElement> getElements()
    {
        return scope.getElements();
    }

    @Override
    public void addElement(ModelElement element)
    {
        scope.addElement(element);
    }
}

