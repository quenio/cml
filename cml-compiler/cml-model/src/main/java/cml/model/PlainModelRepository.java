package cml.model;

import java.util.Optional;
import java.util.Set;

class PlainModelRepository implements ModelRepository
{
    @Override
    public Optional<Target> getTarget(final Model model, final String targetType)
    {
        return model.getTarget(targetType);
    }

    @Override
    public Set<Property> getProperties(final Target target)
    {
        return target.getProperties();
    }

    @Override
    public Set<Concept> getConcepts(final Model model)
    {
        return model.getConcepts();
    }
}
