package cml.generator;

import cml.language.features.model.Model;

public interface Generator
{
    int generate(Model model, final String targetType, final String targetDirPath);
}
