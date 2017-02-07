package cml.generator;

import cml.model.Model;

public interface Generator
{
    int generate(Model model, final String targetType, final String targetDirPath);
}
