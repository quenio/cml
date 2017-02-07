package cml.parser;

import cml.io.Console;
import cml.io.FileSystem;
import cml.model.ModelBuilder;

public class ParserFactory
{
    public static Parser createParser(final Console console, final FileSystem fileSystem, final ModelBuilder modelBuilder)
    {
        return new PlainParser(console, fileSystem, modelBuilder);
    }
}
