package cml.language.foundation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Scope
{
    @Nullable
    private final Scope parent;

    @NotNull
    private final ModelElement owner;

    private final Set<NamedElement> elements = new HashSet<>();

    public Scope(@NotNull ModelElement owner)
    {
        this(null, owner);
    }

    public Scope(@Nullable Scope parent, @NotNull ModelElement owner)
    {
        this.parent = parent;
        this.owner = owner;
    }

    public Optional<NamedElement> findElement(@NotNull String name)
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

    public Set<NamedElement> findLocalConflicts(@NotNull NamedElement element)
    {
        return elements.stream()
                       .filter(e -> !element.equals(e))
                       .filter(e -> e.getName().equals(element.getName()))
                       .collect(Collectors.toSet());
    }
}
