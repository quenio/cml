package cml.model;

import java.util.Optional;

public interface ModelQueries
{
    Optional<Target> getTarget(Model model, String targetType);
}
