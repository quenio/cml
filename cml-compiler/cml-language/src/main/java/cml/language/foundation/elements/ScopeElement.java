package cml.language.foundation.elements;

import java.util.*;

import static java.util.Collections.emptySet;

class ScopeElement
{
    private final Map<Scope, Set<ModelElement>> elements = new HashMap<>();
    private final Map<ModelElement, Scope> parentScope = new HashMap<>();

    static
    {
        final ScopeElement scopeElement = new ScopeElement();

        ScopeImpl.setScopeElement(scopeElement);
        ModelElementImpl.setScopeElement(scopeElement);
    }

    void link(Scope scope, ModelElement modelElement)
    {
        final Set<ModelElement> modelElementList =
            elements.computeIfAbsent(scope, key -> new HashSet<>());

        if (!modelElementList.contains(modelElement))
        {
            modelElementList.add(modelElement);
            parentScope.put(modelElement, scope);
        }
    }

    void unlink(Scope scope, ModelElement modelElement)
    {
        final Set<ModelElement> modelElementList = elements.get(scope);

        if ((modelElementList != null) && modelElementList.contains(modelElement))
        {
            modelElementList.remove(modelElement);
        }

        parentScope.remove(modelElement, scope);
    }

    Set<ModelElement> getElements(Scope scope)
    {
        final Set<ModelElement> modelElementList = elements.get(scope);

        return (modelElementList == null) ? emptySet() : modelElementList;
    }

    Optional<Scope> getParentScope(ModelElement modelElement)
    {
        return Optional.ofNullable(parentScope.get(modelElement));
    }
}
