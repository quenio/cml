package cml.generator;

import java.util.Optional;

public interface Generator
{
    Optional<Target> findTarget(String targetType, String targetDirPath);
}
