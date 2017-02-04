package cml.model;

import java.util.Optional;

class PlainModelQueries implements ModelQueries
{
    @Override
    public Optional<Target> getTarget(final Model model, final String targetType)
    {
        return model.getTarget(targetType);
    }
}
