package cml.language.features.concept;

import cml.language.features.property.PropertyList;
import cml.language.features.property.PropertyListImpl;
import cml.language.foundation.elements.*;

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
