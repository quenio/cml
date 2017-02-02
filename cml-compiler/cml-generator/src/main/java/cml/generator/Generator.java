package cml.generator;

import cml.model.Model;

import java.util.Optional;

public interface Generator
{
    Optional<Target> findTarget(String targetType, String targetDirPath);
    void generate(Model model, Target target);
}
