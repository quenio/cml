package cml.language.foundation;

import java.util.*;

import static java.util.Collections.emptyList;

class ScopeElement
{
    private final Map<Scope, List<ModelElement>> elements = new HashMap<>();
    private final Map<ModelElement, Scope> parentScope = new HashMap<>();

    private static final ScopeElement singleton = new ScopeElement();

    static void init(Object object)
    {
        if (ScopeImpl.class.equals(object))
        {
            ScopeImpl.setScopeElement(singleton);
        }
        else if (ModelElementImpl.class.equals(object))
        {
            ModelElementImpl.setScopeElement(singleton);
        }
    }

    void link(Scope scope, ModelElement modelElement)
    {
        final List<ModelElement> modelElementList =
            elements.computeIfAbsent(scope, key -> new ArrayList<>());

        if (!modelElementList.contains(modelElement))
        {
            modelElementList.add(modelElement);
            parentScope.put(modelElement, scope);
        }
    }

    void unlink(Scope scope, ModelElement modelElement)
    {
        final List<ModelElement> modelElementList = elements.get(scope);

        if ((modelElementList != null) && modelElementList.contains(modelElement))
        {
            modelElementList.remove(modelElement);
        }

        parentScope.remove(modelElement, scope);
    }

    List<ModelElement> getElements(Scope scope)
    {
        final List<ModelElement> modelElementList = elements.get(scope);

        return (modelElementList == null) ? emptyList() : modelElementList;
    }

    Optional<Scope> getParentScope(ModelElement modelElement)
    {
        return Optional.ofNullable(parentScope.get(modelElement));
    }
}
