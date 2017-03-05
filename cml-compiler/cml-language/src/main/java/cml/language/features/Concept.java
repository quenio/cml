package cml.language.features;

import cml.language.foundation.elements.*;

import java.util.List;
import java.util.Optional;

public interface Concept extends NamedElement, PropertyList
{
    static Concept create(String name)
    {
        final ModelElement modelElement = new ModelElementImpl();
        final NamedElement namedElement = new NamedElementImpl(modelElement, name);
        final Scope scope = new ScopeImpl(modelElement);
        final PropertyList propertyList = new PropertyListImpl(scope);
        
        return new ConceptImpl(modelElement, namedElement, propertyList);
    }
}

class ConceptImpl implements Concept
{
    private final ModelElement modelElement;
    private final NamedElement namedElement;
    private final PropertyList propertyList;

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
}
