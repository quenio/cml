package cml.parser;

import cml.io.SourceFile;
import cml.model.Model;

public interface Parser
{
    Model parse(SourceFile sourceFile);
}
