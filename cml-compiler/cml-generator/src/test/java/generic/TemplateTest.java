package generic;


import cml.language.foundation.NamedElement;
import cml.language.foundation.Property;
import cml.language.foundation.Type;
import cml.templates.NameRenderer;
import cml.templates.TemplateGroupFile;
import org.junit.Before;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import java.util.Collection;
import java.util.Locale;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableCollection;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Theories.class)
public abstract class TemplateTest
{
    protected static final Collection<String> commonNames = unmodifiableCollection(asList("SomeConcept", "someVar"));

    private static final String TEMPLATE_GROUP_PATH = "/%s.stg";

    STGroupFile groupFile;

    @Before
    public void setUp()
    {
        groupFile = createTemplateGroupFile(getTemplatePath());

        groupFile.registerRenderer(String.class, new NameRenderer());
    }

    protected ST getTemplate(String templateName)
    {
        return groupFile.getInstanceOf(templateName);
    }

    /**
     * The path to the template file without the file extension.
     * <p>
     * Used to load the template file and to load the expected output files.
     **/
    protected abstract String getTemplatePath();

    protected void testTemplateWithNamedElement(String templateName, NamedElement namedElement, String expectedResult)
    {
        final ST template = getTemplate(templateName);

        template.add("namedElement", namedElement);

        final String result = template.render();

        assertThat(result, is(expectedResult));
    }

    protected void testTemplateWithPropertyAndExpectedResult(String templateName, Property property, String expectedResult)
    {
        final ST template = getTemplate(templateName);

        template.add("property", property);

        final String result = template.render();

        assertThat(result, is(expectedResult));
    }

    protected void testTemplateWithType(String templateName, String typeName, String cardinality, String expectedFormat)
    {
        final ST template = getTemplate(templateName);

        template.add("type", Type.create(typeName, cardinality));

        final String result = template.render();

        assertThat(result, is(format(expectedFormat, pascalCase(typeName))));
    }

    protected static String camelCase(@FromDataPoints("names") String name)
    {
        return name.substring(0, 1).toLowerCase(Locale.getDefault()) + name.substring(1);
    }

    protected static String pascalCase(@FromDataPoints("names") String name)
    {
        return name.substring(0, 1).toUpperCase(Locale.getDefault()) + name.substring(1);
    }

    static TemplateGroupFile createTemplateGroupFile(String templatePath)
    {
        return new TemplateGroupFile(format(TEMPLATE_GROUP_PATH, templatePath));
    }
}
