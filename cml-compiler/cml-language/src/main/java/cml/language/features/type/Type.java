package cml.language.features.type;

import cml.language.foundation.elements.ModelElement;
import cml.language.foundation.elements.ModelElementImpl;
import cml.language.foundation.elements.NamedElement;
import cml.language.foundation.elements.NamedElementImpl;

public interface Type extends NamedElement
{
    static Type create(String name)
    {
        final ModelElement modelElement = new ModelElementImpl();
        final NamedElement namedElement = new NamedElementImpl(modelElement, name);
        return new TypeImpl(modelElement, namedElement);
    }
}
