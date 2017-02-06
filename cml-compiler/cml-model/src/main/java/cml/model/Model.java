package cml.model;

import java.util.*;

import static java.util.Collections.unmodifiableSet;

public class Model
{
    // Associations:

    private final Set<Concept> concepts = new LinkedHashSet<>();

    Set<Concept> getConcepts()
    {
        return unmodifiableSet(concepts);
    }

    void addConcept(final Concept concept)
    {
        concepts.add(concept);
    }

    //---

    private final Map<String, Target> targets = new HashMap<>();

    Optional<Target> getTarget(final String targetType)
    {
        return Optional.ofNullable(targets.get(targetType));
    }

    void addTarget(final Target target)
    {
        targets.put(target.getTargetType(), target);
    }
}
