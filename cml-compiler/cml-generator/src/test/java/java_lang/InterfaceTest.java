package java_lang;

import cml.language.features.Concept;
import cml.language.foundation.Property;
import cml.language.foundation.Type;
import generic.TemplateTest;
import org.junit.Test;

import java.io.IOException;

public class InterfaceTest extends TemplateTest
{
    @Override
    protected String getTemplateFileName()
    {
        return "/java_lang/interface.stg";
    }

    @Test
    public void interface_emptyConcept() throws IOException
    {
        final Concept concept = Concept.create("EmptyBook");

        testInterfaceTemplateWithConcept(concept, "emptyConcept.txt");
    }

    @Test
    public void interface_simpleConcept() throws IOException
    {
        final Concept concept = Concept.create("StringTitledBook");

        concept.addElement(Property.create("title", null, Type.create("String", null)));

        testInterfaceTemplateWithConcept(concept, "simpleConcept.txt");
    }

    @Test
    public void interface_conceptWithOptionalProperty() throws IOException
    {
        final Concept concept = Concept.create("Book");

        concept.addElement(Property.create("title", null, Type.create("String", null)));
        concept.addElement(Property.create("sequel", null, Type.create("Book", "?")));

        testInterfaceTemplateWithConcept(concept, "conceptWithOptionalProperty.txt");
    }

    @Test
    public void interface_conceptWithSetProperty() throws IOException
    {
        final Concept concept = Concept.create("Book");

        concept.addElement(Property.create("title", null, Type.create("String", null)));
        concept.addElement(Property.create("sequel", null, Type.create("Book", "?")));
        concept.addElement(Property.create("categories", null, Type.create("Category", "*")));

        testInterfaceTemplateWithConcept(concept, "conceptWithSetProperty.txt");
    }

    private void testInterfaceTemplateWithConcept(Concept concept, String expectedOutputFileName) throws IOException
    {
        testTemplateWithConcept(
            "interface",
            concept,
            "/java_lang/interface/" + expectedOutputFileName);
    }
}
