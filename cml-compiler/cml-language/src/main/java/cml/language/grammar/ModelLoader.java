package cml.language.grammar;

import cml.io.Console;
import cml.io.SourceFile;
import cml.language.features.model.Model;

import java.util.Optional;

public interface ModelLoader
{
    Optional<Model> loadModel(SourceFile sourceFile);

    static ModelLoader create(Console console)
    {
        return new ModelLoaderImpl(console);
    }
}
