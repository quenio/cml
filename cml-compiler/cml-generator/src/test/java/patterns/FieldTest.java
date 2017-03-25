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
    public void fieldName()
    {
        for (String name : commonNameFormats)
        {
            testFieldName(name);
        }
    }

    @Test
    public void fieldTypeDecl_optional() throws IOException
    {
        final String cardinality = "?"; // optional

        testFieldTypeDecl(cardinality, "type_optional.txt");
    }

    @Test
    public void fieldTypeDecl_required() throws IOException
    {
        final String cardinality = null; // required

        testFieldTypeDecl(cardinality, "type_required.txt");
    }

    @Test
    public void fieldTypeDecl_set() throws IOException
    {
        final String cardinality = "*"; // set

        testFieldTypeDecl(cardinality, "type_set.txt");
    }

    private void testFieldName(String name)
    {
        testTemplateWithNamedElement("fieldName", Concept.create(name), camelCase(name));
    }

    private void testFieldTypeDecl(String cardinality, String expectedOutputPath) throws IOException
    {
        for (String name : commonNameFormats)
        {
            final Type type = Type.create(name, cardinality);

            testTemplateWithType("fieldTypeDecl", type, expectedOutputPath);
        }
    }
}
