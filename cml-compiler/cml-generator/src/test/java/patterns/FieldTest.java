package patterns;

import cml.language.features.Concept;
import cml.language.foundation.Type;
import generic.TemplateLangTest;
import org.junit.Test;

import java.io.IOException;

public class FieldTest extends TemplateLangTest
{
    public FieldTest(String targetLanguage)
    {
        super(targetLanguage);
    }

    @Override
    protected String getTemplatePath()
    {
        return "patterns/field";
    }

    @Test
    public void field_name() throws IOException
    {
        for (String name : commonNameFormats)
        {
            field_name(name);
        }
    }

    @Test
    public void field_type__optional() throws IOException
    {
        final String cardinality = "?"; // optional

        field_type(cardinality, "optional.txt");
    }

    @Test
    public void field_type__required() throws IOException
    {
        final String cardinality = null; // required

        field_type(cardinality, "required.txt");
    }

    @Test
    public void field_type__set() throws IOException
    {
        final String cardinality = "*"; // set

        field_type(cardinality, "set.txt");
    }

    private void field_name(String name) throws IOException
    {
        testTemplateWithNamedElement("field_name", Concept.create(name), "expected.txt");
    }

    private void field_type(String cardinality, String expectedOutputPath) throws IOException
    {
        for (String name : commonNameFormats)
        {
            final Type type = Type.create(name, cardinality);

            testTemplateWithType("field_type", type, expectedOutputPath);
        }
    }
}
