package cml.language.features.model;

import cml.language.features.concept.Concept;
import cml.language.features.target.Target;
import cml.language.foundation.elements.ModelElement;
import cml.language.foundation.elements.ModelElementImpl;
import cml.language.foundation.elements.Scope;
import cml.language.foundation.elements.ScopeImpl;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public interface Model extends Scope
{
    default Set<Concept> getConcepts()
    {
        return getElements().stream()
                            .filter(e -> e instanceof Concept)
                            .map(e -> (Concept)e)
                            .collect(toSet());
    }

    default Set<Target> getTargets()
    {
        return getElements().stream()
                            .filter(e -> e instanceof Target)
                            .map(e -> (Target)e)
                            .collect(toSet());
    }

    static Model create()
    {
        final ModelElement modelElement = new ModelElementImpl();
        final Scope scope = new ScopeImpl(modelElement);
        
        return new ModelImpl(modelElement, scope);
    }
}
