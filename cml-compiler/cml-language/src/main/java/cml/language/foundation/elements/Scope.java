package cml.language.foundation.elements;

import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public interface Scope extends ModelElement
{
    Set<ModelElement> getElements();
    void addElement(ModelElement element);

    default Set<NamedElement> getNamedElements()
    {
        return getElements().stream()
                            .filter(e -> e instanceof NamedElementImpl)
                            .map(e -> (NamedElement)e)
                            .collect(toSet());
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

    default Set<NamedElement> findLocalConflicts(NamedElement element)
    {
        return getNamedElements().stream()
                                 .filter(e -> !element.equals(e))
                                 .filter(e -> e.getName().equals(element.getName()))
                                 .collect(toSet());
    }
}
