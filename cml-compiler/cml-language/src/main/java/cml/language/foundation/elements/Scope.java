package cml.language.foundation.elements;

import java.util.List;
import java.util.Optional;

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

    default List<NamedElement> findLocalConflicts(NamedElement element)
    {
        return getNamedElements().stream()
                                 .filter(e -> !element.equals(e))
                                 .filter(e -> e.getName().equals(element.getName()))
                                 .collect(toList());
    }
}
