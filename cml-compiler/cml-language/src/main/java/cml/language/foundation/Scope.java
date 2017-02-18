package cml.language.foundation;

import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

class Scope
{
    private final @Nullable Scope parent;
    private final ModelElement owner;
    private final Set<NamedElement> elements = new HashSet<>();

    Scope(ModelElement owner)
    {
        this(null, owner);
    }

    Scope(@Nullable Scope parent, ModelElement owner)
    {
        this.parent = parent;
        this.owner = owner;
    }

    Optional<NamedElement> findElement(String name)
    {
        assert !name.matches("\\s*") : "require not name = /\\s*/";

        final Optional<NamedElement> element = elements.stream()
                                                       .filter(e -> name.equals(e.getName()))
                                                       .findFirst();

        if (!element.isPresent() && (parent != null))
        {
            return parent.findElement(name);
        }

        return element;
    }

    Set<NamedElement> findLocalConflicts(NamedElement element)
    {
        return elements.stream()
                       .filter(e -> !element.equals(e))
                       .filter(e -> e.getName().equals(element.getName()))
                       .collect(Collectors.toSet());
    }
}
