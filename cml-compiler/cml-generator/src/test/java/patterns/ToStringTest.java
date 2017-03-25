package patterns;

import cml.language.features.Concept;
import cml.language.foundation.Property;
import cml.language.foundation.Type;
import generic.TemplateLangTest;
import org.junit.Test;

import java.io.IOException;

public class ToStringTest  extends TemplateLangTest
{
    public ToStringTest(String targetLanguage)
    {
        super(targetLanguage);
    }

    @Override
    protected String getTemplatePath()
    {
        return "patterns/to_string";
    }

    @Test
    public void toString_noProperties() throws IOException
    {
        final Concept concept = Concept.create("SomeConcept");

        testToStringWithConcept(concept, "noProperties.txt");
    }

    @Test
    public void toString_requiredProperty() throws IOException
    {
        final Concept concept = Concept.create("SomeConcept");

        concept.addElement(Property.create("someProperty", null, Type.create("SomeType", null)));

        testToStringWithConcept(concept, "requiredProperty.txt");
    }

    @Test
    public void toString_optionalProperty() throws IOException
    {
        final Concept concept = Concept.create("SomeConcept");

        concept.addElement(Property.create("someProperty", null, Type.create("SomeType", null)));
        concept.addElement(Property.create("optionalProperty", null, Type.create("AnotherType", "?")));

        testToStringWithConcept(concept, "optionalProperty.txt");
    }

    @Test
    public void toString_setProperty() throws IOException
    {
        final Concept concept = Concept.create("SomeConcept");

        concept.addElement(Property.create("someProperty", null, Type.create("SomeType", null)));
        concept.addElement(Property.create("optionalProperty", null, Type.create("AnotherType", "?")));
        concept.addElement(Property.create("setProperty", null, Type.create("YetAnotherType", "*")));

        testToStringWithConcept(concept, "setProperty.txt");
    }

    private void testToStringWithConcept(Concept concept, String expectedOutputFileName) throws IOException
    {
        testTemplateWithConcept("to_string", concept, expectedOutputFileName);
    }
}
