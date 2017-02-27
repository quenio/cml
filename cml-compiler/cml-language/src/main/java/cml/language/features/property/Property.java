package cml.language.features.property;

import cml.language.features.type.TypedElement;
import cml.language.features.type.TypedElementImpl;
import cml.language.foundation.elements.*;

public interface Property extends TypedElement
{
    String getValue();

    static Property create(String name, String value)
    {
        final ModelElement modelElement = new ModelElementImpl();
        final NamedElement namedElement = new NamedElementImpl(modelElement, name);
        final TypedElement typedElement = new TypedElementImpl(namedElement);
        return new PropertyImpl(typedElement, value);
    }
}
