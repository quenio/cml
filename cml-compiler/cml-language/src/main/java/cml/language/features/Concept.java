package cml.language.features;

import cml.language.foundation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

public interface Concept extends NamedElement, PropertyList
{
    List<Concept> getAncestors();
    void addAncestor(Concept concept);

    @SuppressWarnings("unused")
    List<String> getMissingAncestors();
    void addMissingAncestor(String missingAncestor);

    default List<Property> getInheritedProperties()
    {
        return getAncestors().stream()
                             .flatMap(concept -> concept.getProperties().stream())
                             .collect(toList());
    }

    @SuppressWarnings("unused")
    default List<Property> getAllProperties()
    {
        return concat(getInheritedProperties().stream(), getProperties().stream()).collect(toList());
    }

    static Concept create(String name)
    {
        return new ConceptImpl(name);
    }
}

class ConceptImpl implements Concept
{
    private final ModelElement modelElement;
    private final NamedElement namedElement;
    private final Scope scope;
    private final List<Concept> ancestors = new ArrayList<>();
    private final List<String> missingAncestors = new ArrayList<>();

    ConceptImpl(String name)
    {
        this.modelElement = ModelElement.create(this);
        this.namedElement = NamedElement.create(modelElement, name);
        this.scope = Scope.create(this, modelElement);
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
        return scope.getElements();
    }

    @Override
    public void addElement(ModelElement element)
    {
        scope.addElement(element);
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
