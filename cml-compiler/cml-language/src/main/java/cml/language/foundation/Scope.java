package cml.language.foundation;

import java.util.*;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public interface Scope extends ModelElement
{
    List<ModelElement> getElements();
    void addElement(ModelElement element);

    default List<NamedElement> getNamedElements()
    {
        return getElements().stream()
                            .filter(e -> e instanceof NamedElementImpl)
                            .map(e -> (NamedElement)e)
                            .collect(toList());
    }

    @SuppressWarnings("unused")
    default Optional<NamedElement> findElement(String name)
    {
        assert !name.matches("\\s*") : "require not name = /\\s*/";

        final Optional<NamedElement> element = getNamedElements().stream()
                                                                 .filter(e -> name.equals(e.getName()))
                                                                 .findFirst();

        if (!element.isPresent() && getParentScope().isPresent())
        {
            return getParentScope().get().findElement(name);
        }

        return element;
    }

    @SuppressWarnings("unused")
    default List<NamedElement> findLocalConflicts(NamedElement element)
    {
        return getNamedElements().stream()
                                 .filter(e -> !element.equals(e))
                                 .filter(e -> e.getName().equals(element.getName()))
                                 .collect(toList());
    }

    static Scope create(Scope self, ModelElement modelElement)
    {
        return new ScopeImpl(self, modelElement);
    }
}

class ScopeImpl implements Scope
{
    private final Scope self;
    private final ModelElement modelElement;

    ScopeImpl(Scope self, ModelElement modelElement)
    {
        this.self = self;
        this.modelElement = modelElement;
    }

    @Override
    public Optional<Scope> getParentScope()
    {
        return modelElement.getParentScope();
    }

    @Override
    public List<ModelElement> getElements()
    {
        return scopeElement.getElements(self);
    }

    @Override
    public void addElement(ModelElement element)
    {
        scopeElement.link(self, element);
    }

    private static ScopeElement scopeElement;

    static void setScopeElement(ScopeElement association)
    {
        assert scopeElement == null;

        scopeElement = association;
    }

    static
    {
        ScopeElement.init(ScopeImpl.class);
    }
}

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

    @SuppressWarnings("unused")
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

