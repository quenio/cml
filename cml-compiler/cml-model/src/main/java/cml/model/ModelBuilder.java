package cml.model;

public interface ModelBuilder
{
    void newModel();
    Model getModel();

    void addConcept(String name);
    void addTarget(String targetType);
    void addProperty(String propertyName, String propertyValue);
}
