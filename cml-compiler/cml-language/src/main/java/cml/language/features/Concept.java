package cml.language.features;

import cml.language.foundation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.unmodifiableList;

public interface Concept extends NamedElement, PropertyList
{
    List<Type> getAncestors();

    void addAncestor(Type type);

    static Concept create(String name)
    {
        final ModelElement modelElement = ModelElement.create();
        final NamedElement namedElement = NamedElement.create(modelElement, name);
        final Scope scope = Scope.create(modelElement);
        final PropertyList propertyList = PropertyList.create(scope);
        
        return new ConceptImpl(modelElement, namedElement, propertyList);
    }
}

class ConceptImpl implements Concept
{
    private final ModelElement modelElement;
    private final NamedElement namedElement;
    private final PropertyList propertyList;
    private final List<Type> ancestors = new ArrayList<>();

    ConceptImpl(ModelElement modelElement, NamedElement namedElement, PropertyList propertyList)
    {
        this.modelElement = modelElement;
        this.namedElement = namedElement;
        this.propertyList = propertyList;
    }

    @Override
    public Optional<Scope> getParentScope()
    {
        return modelElement.getParentScope();
    }

    @Override
    public String getName()
    {
        return namedElement.getName();
    }

    @Override
    public List<ModelElement> getElements()
    {
        return propertyList.getElements();
    }

    @Override
    public void addElement(ModelElement element)
    {
        propertyList.addElement(element);
    }

    @Override
    public List<Type> getAncestors()
    {
        return unmodifiableList(ancestors);
    }

    @Override
    public void addAncestor(Type type)
    {
        assert !ancestors.contains(type);

        ancestors.add(type);
    }
}
