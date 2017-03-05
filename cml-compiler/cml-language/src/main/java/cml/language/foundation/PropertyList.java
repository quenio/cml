package cml.language.foundation;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public interface PropertyList extends Scope
{
    default List<Property> getProperties()
    {
        return getElements().stream()
                            .filter(e -> e instanceof PropertyImpl)
                            .map(e -> (Property)e)
                            .collect(toList());
    }

    static PropertyList create(Scope scope)
    {
        return new PropertyListImpl(scope);
    }
}

class PropertyListImpl implements PropertyList
{
    private final Scope scope;

    PropertyListImpl(Scope scope)
    {
        this.scope = scope;
    }

    @Override
    public Optional<Scope> getParentScope()
    {
        return scope.getParentScope();
    }

    @Override
    public List<ModelElement> getElements()
    {
        return scope.getElements();
    }

    @Override
    public void addElement(ModelElement element)
    {
        scope.addElement(element);
    }
}
