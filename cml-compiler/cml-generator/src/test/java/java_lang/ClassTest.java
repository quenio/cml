package java_lang;

import cml.language.features.Concept;
import cml.language.foundation.Property;
import cml.language.foundation.Type;
import generic.TemplateTest;
import org.junit.Test;

import java.io.IOException;

public class ClassTest extends TemplateTest
{
    @Override
    protected String getTemplateFileName()
    {
        return "/java_lang/class.stg";
    }

    @Test
    public void class_emptyConcept() throws IOException
    {
        final Concept concept = Concept.create("Book");

        testInterfaceTemplateWithConcept(concept, "emptyConcept.txt");
    }

    @Test
    public void class_simpleConcept() throws IOException
    {
        final Concept concept = Concept.create("Book");

        concept.addElement(Property.create("title", null, Type.create("String", null)));

        testInterfaceTemplateWithConcept(concept, "simpleConcept.txt");
    }

    private void testInterfaceTemplateWithConcept(Concept concept, String expectedOutputFileName) throws IOException
    {
        testTemplateWithConcept(
            "class",
            concept,
            "/java_lang/class/" + expectedOutputFileName);
    }
}
