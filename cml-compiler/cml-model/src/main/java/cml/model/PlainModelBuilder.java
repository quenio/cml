package cml.model;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

class PlainModelBuilder implements ModelBuilder
{
    private final List<String> conceptNames = new ArrayList<>();

    @Override
    public void addConcept(final String name)
    {
        conceptNames.add(name);
    }

    @Override
    public Model buildModel()
    {
        final List<Concept> concepts = conceptNames.stream().map(Concept::new).collect(toList());
        return new Model(concepts);
    }
}
