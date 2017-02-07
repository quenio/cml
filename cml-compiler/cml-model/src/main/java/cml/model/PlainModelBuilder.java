package cml.model;

import java.util.List;
import java.util.Set;

class PlainModelBuilder implements ModelBuilder
{
    private Model model = new Model();
    private Target currentTarget;

    @Override
    public void newModel()
    {
        model = new Model();
    }

    @Override
    public Model getModel()
    {
        return model;
    }

    @Override
    public void addConcept(final String name)
    {
        model.addConcept(new Concept(name));
    }

    @Override
    public void addTarget(final String targetType)
    {
        currentTarget = new Target(targetType);
        model.addTarget(currentTarget);
    }

    @Override
    public void addProperty(final String propertyName, final String propertyValue)
    {
        if (currentTarget != null)
        {
            currentTarget.addProperty(new Property(propertyName, propertyValue));
        }
    }
}
