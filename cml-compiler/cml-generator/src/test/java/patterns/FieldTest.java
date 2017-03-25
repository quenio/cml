package patterns;

import cml.language.features.Concept;
import generic.TemplateLangTest;
import org.junit.Test;

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
        for (String name: commonNames)
        {
            testFieldName(name);
        }
    }

    @Test
    public void fieldTypeDecl_required()
    {
        final String cardinality = null; // required

        testFieldTypeDecl(cardinality, "%s");
    }

    @Test
    public void fieldTypeDecl_optional()
    {
        final String cardinality = "?"; // optional

        testFieldTypeDecl(cardinality, "@Nullable %s");
    }

    @Test
    public void fieldTypeDecl_set()
    {
        final String cardinality = "*"; // set

        testFieldTypeDecl(cardinality, "Set<%s>");
    }

    private void testFieldName(String name)
    {
        testTemplateWithNamedElement("fieldName", Concept.create(name), camelCase(name));
    }

    private void testFieldTypeDecl(String cardinality, String expectedFormat)
    {
        for (String name: commonNames)
        {
            testTemplateWithType("fieldTypeDecl", name, cardinality, expectedFormat);
        }
    }
}
