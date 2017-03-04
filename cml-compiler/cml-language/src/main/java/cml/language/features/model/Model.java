package cml.language.features.model;

import cml.language.features.concept.Concept;
import cml.language.features.target.Target;
import cml.language.foundation.elements.ModelElement;
import cml.language.foundation.elements.ModelElementImpl;
import cml.language.foundation.elements.Scope;
import cml.language.foundation.elements.ScopeImpl;

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
        final ModelElement modelElement = new ModelElementImpl();
        final Scope scope = new ScopeImpl(modelElement);
        
        return new ModelImpl(modelElement, scope);
    }
}
