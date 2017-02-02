package cml.model;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Model
{
    private final List<Concept> concepts;

    Model(final List<Concept> concepts)
    {
        this.concepts = unmodifiableList(concepts);
    }

    public List<Concept> getConcepts()
    {
        return concepts;
    }
}
