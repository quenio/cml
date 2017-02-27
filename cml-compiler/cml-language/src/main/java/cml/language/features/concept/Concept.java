package cml.language.features.concept;

import cml.language.features.property.PropertySet;
import cml.language.features.property.PropertySetImpl;
import cml.language.foundation.elements.*;

public interface Concept extends NamedElement, PropertySet
{
    static Concept create(String name)
    {
        final ModelElement modelElement = new ModelElementImpl();
        final NamedElement namedElement = new NamedElementImpl(modelElement, name);
        final Scope scope = new ScopeImpl(modelElement);
        final PropertySet propertySet = new PropertySetImpl(scope);
        
        return new ConceptImpl(modelElement, namedElement, propertySet);
    }
}
