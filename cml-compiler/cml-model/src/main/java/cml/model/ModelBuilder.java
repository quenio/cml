package cml.model;

public interface ModelBuilder
{
    void addConcept(String name);

    Model buildModel();
}
