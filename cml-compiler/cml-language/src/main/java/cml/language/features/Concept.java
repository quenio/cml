package cml.language.features;

import cml.language.foundation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

public interface Concept extends NamedElement, PropertyList
{
    List<Concept> getAncestors();
    void addAncestor(Concept concept);

    List<String> getMissingAncestors();
    void addMissingAncestor(String missingAncestor);

    default List<Property> getInheritedProperties()
    {
        return getAncestors().stream()
                             .flatMap(concept -> concept.getProperties().stream())
                             .collect(toList());
    }

    default List<Property> getAllProperties()
    {
        return concat(getInheritedProperties().stream(), getProperties().stream()).collect(toList());
    }

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
    private final List<Concept> ancestors = new ArrayList<>();
    private final List<String> missingAncestors = new ArrayList<>();

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
    public List<Concept> getAncestors()
    {
        return unmodifiableList(ancestors);
    }

    @Override
    public void addAncestor(Concept concept)
    {
        assert !ancestors.contains(concept);

        ancestors.add(concept);
    }

    @Override
    public List<String> getMissingAncestors()
    {
        return unmodifiableList(missingAncestors);
    }

    @Override
    public void addMissingAncestor(String missingAncestor)
    {
        assert !missingAncestors.contains(missingAncestor);

        missingAncestors.add(missingAncestor);
    }
}
