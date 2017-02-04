package cml.generator;

import java.util.Optional;

public interface TargetRepository
{
    Optional<Target> createTarget(String targetType, String targetDirPath);
}
