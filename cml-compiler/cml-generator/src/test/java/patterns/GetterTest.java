package patterns;

import cml.language.foundation.Property;
import cml.language.foundation.Type;
import generic.TemplateLangTest;
import org.junit.Test;

import java.io.IOException;

public class GetterTest extends TemplateLangTest
{
    public GetterTest(String targetLanguage)
    {
        super(targetLanguage);
    }

    @Override
    protected String getTemplatePath()
    {
        return "patterns/getter";
    }

    @Test
    public void getterTypeDecl_required()
    {
        final String cardinality = null; // required

        testGetterTypeDecl(cardinality, "%s");
    }

    @Test
    public void getterTypeDecl_optional()
    {
        final String cardinality = "?"; // optional

        testGetterTypeDecl(cardinality, "Optional<%s>");
    }

    @Test
    public void getterTypeDecl_set()
    {
        final String cardinality = "*"; // set

        testGetterTypeDecl(cardinality, "Set<%s>");
    }

    @Test
    public void getterInterfaceDecl_requiredProperty() throws IOException
    {
        final String cardinality = null; // required
        final Property property = Property.create("SomeProperty", null, Type.create("someType", cardinality));

        testGetterInterfaceDeclWithProperty(property, "SomeType getSomeProperty();");
    }

    @Test
    public void getterInterfaceDecl_optionalProperty() throws IOException
    {
        final String cardinality = "?"; // optional
        final Property property = Property.create("SomeProperty", null, Type.create("someType", cardinality));

        testGetterInterfaceDeclWithProperty(property, "Optional<SomeType> getSomeProperty();");
    }

    @Test
    public void getterInterfaceDecl_setProperty() throws IOException
    {
        final String cardinality = "*"; // set
        final Property property = Property.create("SomeProperty", null, Type.create("someType", cardinality));

        testGetterInterfaceDeclWithProperty(property, "Set<SomeType> getSomeProperty();");
    }

    @Test
    public void getterClassDecl_requiredProperty() throws IOException
    {
        final String cardinality = null; // required
        final Property property = Property.create("SomeProperty", null, Type.create("someType", cardinality));

        testGetterClassDeclWithProperty(property, "requiredProperty.txt");
    }

    @Test
    public void getterClassDecl_optionalProperty() throws IOException
    {
        final String cardinality = "?"; // optional
        final Property property = Property.create("SomeProperty", null, Type.create("someType", cardinality));

        testGetterClassDeclWithProperty(property, "optionalProperty.txt");
    }

    @Test
    public void getterClassDecl_setProperty() throws IOException
    {
        final String cardinality = "*"; // set
        final Property property = Property.create("SomeProperty", null, Type.create("someType", cardinality));

        testGetterClassDeclWithProperty(property, "setProperty.txt");
    }

    private void testGetterTypeDecl(String cardinality, String expectedFormat)
    {
        for (String name: commonNames)
        {
            testTemplateWithType("getterTypeDecl", name, cardinality, expectedFormat);
        }
    }

    private void testGetterInterfaceDeclWithProperty(Property property, String expectedOutput)
    {
        testTemplateWithPropertyAndExpectedResult("getterInterfaceDecl", property, expectedOutput);
    }

    private void testGetterClassDeclWithProperty(Property property, String expectedOutputFileName) throws IOException
    {
        testTemplateWithProperty(
            "getterClassDecl",
            property,
            "getterClassDecl/" + expectedOutputFileName);
    }
}
