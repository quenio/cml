package cml.model;

import java.util.Optional;

public interface ModelRepository
{
    Optional<Target> getTarget(Model model, String targetType);
}
