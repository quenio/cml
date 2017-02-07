package cml.model;

import java.util.Optional;
import java.util.Set;

public interface ModelRepository
{
    Optional<Target> getTarget(Model model, String targetType);

    Set<Property> getProperties(Target target);

    Set<Concept> getConcepts(Model model);
}
