package cml.parser;

import cml.io.Directory;
import cml.model.Model;

import java.util.Optional;

public interface Parser
{
    Optional<Model> parse(Directory sourceDir);
}
