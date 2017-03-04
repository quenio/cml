package cml.language.features.target;

import cml.language.features.property.PropertyList;
import cml.language.features.property.PropertyListImpl;
import cml.language.foundation.elements.*;

public interface Target extends NamedElement, PropertyList
{
    static Target create(String name)
    {
        final ModelElement modelElement = new ModelElementImpl();
        final NamedElement namedElement = new NamedElementImpl(modelElement, name);
        final Scope scope = new ScopeImpl(modelElement);
        final PropertyList propertyList = new PropertyListImpl(scope);

        return new TargetImpl(modelElement, namedElement, propertyList);
    }
}
