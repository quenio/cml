package java_lang;

import cml.language.features.Concept;
import cml.language.foundation.NamedElement;
import cml.language.foundation.Property;
import cml.language.foundation.Type;
import generic.TemplateTest;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theory;
import org.stringtemplate.v4.ST;

import java.io.IOException;
import java.util.Locale;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CommonTest extends TemplateTest
{
    private static final String REQUIRED_FIELD_TYPE_DECL = "%s";
    private static final String OPTIONAL_FIELD_TYPE_DECL = "@Nullable %s";
    private static final String SET_FIELD_TYPE_DECL = "Set<%s>";

    private static final String REQUIRED_GETTER_TYPE_DECL = "%s";
    private static final String OPTIONAL_GETTER_TYPE_DECL = "Optional<%s>";
    private static final String SET_GETTER_TYPE_DECL = "Set<%s>";

    @DataPoints("names")
    public static final Object[] names = { "SomeConcept", "someVar" };

    @Override
    protected String getTemplateFileName()
    {
        return "/java_lang/common.stg";
    }

    @Theory
    public void fieldName(@FromDataPoints("names") String name)
    {
        testTemplateWithNamedElement("fieldName", Concept.create(name), camelCase(name));
    }

    @Theory
    public void typeName(@FromDataPoints("names") String name)
    {
        testTemplateWithNamedElement("typeName", Type.create(name, null), pascalCase(name));
    }

    @Theory
    public void fieldTypeDecl_required(@FromDataPoints("names") String typeName)
    {
        final String cardinality = null; // required

        testTemplateWithType("fieldTypeDecl", typeName, cardinality, REQUIRED_FIELD_TYPE_DECL);
    }

    @Theory
    public void fieldTypeDecl_optional(@FromDataPoints("names") String typeName)
    {
        final String cardinality = "?"; // optional

        testTemplateWithType("fieldTypeDecl", typeName, cardinality, OPTIONAL_FIELD_TYPE_DECL);
    }

    @Theory
    public void fieldTypeDecl_set(@FromDataPoints("names") String typeName)
    {
        final String cardinality = "*"; // set

        testTemplateWithType("fieldTypeDecl", typeName, cardinality, SET_FIELD_TYPE_DECL);
    }

    @Theory
    public void getterTypeDecl_required(@FromDataPoints("names") String typeName)
    {
        final String cardinality = null; // required

        testTemplateWithType("getterTypeDecl", typeName, cardinality, REQUIRED_GETTER_TYPE_DECL);
    }

    @Theory
    public void getterTypeDecl_optional(@FromDataPoints("names") String typeName)
    {
        final String cardinality = "?"; // optional

        testTemplateWithType("getterTypeDecl", typeName, cardinality, OPTIONAL_GETTER_TYPE_DECL);
    }

    @Theory
    public void getterTypeDecl_set(@FromDataPoints("names") String typeName)
    {
        final String cardinality = "*"; // set

        testTemplateWithType("getterTypeDecl", typeName, cardinality, SET_GETTER_TYPE_DECL);
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

    @Test
    public void getterDecl_requiredProperty() throws IOException
    {
        final String cardinality = null; // required
        final Property property = Property.create("someProperty", null, Type.create("SomeType", cardinality));

        testTemplateWithPropertyAndExpectedResult(
            "getterDecl",
            property,
            "SomeType getSomeProperty();");
    }

    @Test
    public void getterDecl_optionalProperty() throws IOException
    {
        final String cardinality = "?"; // optional
        final Property property = Property.create("someProperty", null, Type.create("SomeType", cardinality));

        testTemplateWithPropertyAndExpectedResult(
            "getterDecl",
            property,
            "Optional<SomeType> getSomeProperty();");
    }

    @Test
    public void getterDecl_setProperty() throws IOException
    {
        final String cardinality = "*"; // set
        final Property property = Property.create("someProperty", null, Type.create("SomeType", cardinality));

        testTemplateWithPropertyAndExpectedResult(
            "getterDecl",
            property,
            "Set<SomeType> getSomeProperty();");
    }

    @Test
    public void getterImpl_requiredProperty() throws IOException
    {
        final String cardinality = null; // required
        final Property property = Property.create("someProperty", null, Type.create("SomeType", cardinality));

        testGetterImplWithProperty(property, "requiredProperty.txt");
    }

    @Test
    public void getterImpl_optionalProperty() throws IOException
    {
        final String cardinality = "?"; // optional
        final Property property = Property.create("someProperty", null, Type.create("SomeType", cardinality));

        testGetterImplWithProperty(property, "optionalProperty.txt");
    }

    @Test
    public void getterImpl_setProperty() throws IOException
    {
        final String cardinality = "*"; // set
        final Property property = Property.create("someProperty", null, Type.create("SomeType", cardinality));

        testGetterImplWithProperty(property, "setProperty.txt");
    }

    private void testTemplateWithNamedElement(String templateName, NamedElement namedElement, String expectedResult)
    {
        final ST template = getTemplate(templateName);

        template.add("namedElement", namedElement);

        final String result = template.render();

        assertThat(result, is(expectedResult));
    }

    private void testTemplateWithType(String templateName, String typeName, String cardinality, String expectedFormat)
    {
        final ST template = getTemplate(templateName);

        template.add("type", Type.create(typeName, cardinality));

        final String result = template.render();

        assertThat(result, is(format(expectedFormat, pascalCase(typeName))));
    }

    private void testTemplateWithConcept(String templateName, Concept concept, String expectedOutputPath)
        throws IOException
    {
        final ST template = getTemplate(templateName);

        template.add("concept", concept);

        final String result = template.render();

        assertThatOutputMatches(expectedOutputPath, result);
    }

    private void testTemplateWithPropertyAndExpectedResult(String templateName, Property property, String expectedResult)
    {
        final ST template = getTemplate(templateName);

        template.add("property", property);

        final String result = template.render();

        assertThat(result, is(expectedResult));
    }

    private void testTemplateWithPropertyAndExpectedOutput(String templateName, Property property, String expectedOutputPath)
        throws IOException
    {
        final ST template = getTemplate(templateName);

        template.add("property", property);

        final String result = template.render();

        assertThatOutputMatches(expectedOutputPath, result);
    }

    private void testToStringWithConcept(Concept concept, String expectedOutputFileName) throws IOException
    {
        testTemplateWithConcept(
            "toString",
            concept,
            "/java_lang/common/toString/" + expectedOutputFileName);
    }

    private void testGetterImplWithProperty(Property property, String expectedOutputFileName) throws IOException
    {
        testTemplateWithPropertyAndExpectedOutput(
            "getterImpl",
            property,
            "/java_lang/common/getterImpl/" + expectedOutputFileName);
    }

    private static String camelCase(@FromDataPoints("names") String name)
    {
        return name.substring(0, 1).toLowerCase(Locale.getDefault()) + name.substring(1);
    }

    private static String pascalCase(@FromDataPoints("names") String name)
    {
        return name.substring(0, 1).toUpperCase(Locale.getDefault()) + name.substring(1);
    }
}
