package cml.generator;

import cml.model.Model;

public interface Generator
{
    void generate(Model model, Target target);
}
