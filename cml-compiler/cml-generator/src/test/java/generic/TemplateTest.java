package generic;


import cml.language.features.Concept;
import cml.language.foundation.NamedElement;
import cml.language.foundation.Property;
import cml.templates.NameRenderer;
import cml.templates.TemplateGroupFile;
import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Theories.class)
public abstract class TemplateTest
{
    private static final Charset OUTPUT_FILE_ENCODING = Charset.forName("UTF-8");

    private STGroupFile groupFile;

    @Before
    public void setUp()
    {
        groupFile = new TemplateGroupFile(getTemplateFileName());

        groupFile.registerRenderer(String.class, new NameRenderer());
    }

    protected ST getTemplate(String templateName)
    {
        return groupFile.getInstanceOf(templateName);
    }

    protected abstract String getTemplateFileName();

    protected void testTemplateWithNamedElement(String templateName, NamedElement namedElement, String expectedResult)
    {
        final ST template = getTemplate(templateName);

        template.add("namedElement", namedElement);

        final String result = template.render();

        assertThat(result, is(expectedResult));
    }

    protected void testTemplateWithConcept(String templateName, Concept concept, String expectedOutputPath)
        throws IOException
    {
        final ST template = getTemplate(templateName);

        template.add("concept", concept);

        final String result = template.render();

        assertThatOutputMatches(expectedOutputPath, result);
    }

    protected void testTemplateWithPropertyAndExpectedResult(String templateName, Property property, String expectedResult)
    {
        final ST template = getTemplate(templateName);

        template.add("property", property);

        final String result = template.render();

        assertThat(result, is(expectedResult));
    }

    protected void testTemplateWithPropertyAndExpectedOutput(String templateName, Property property, String expectedOutputPath)
        throws IOException
    {
        final ST template = getTemplate(templateName);

        template.add("property", property);

        final String result = template.render();

        assertThatOutputMatches(expectedOutputPath, result);
    }

    private void assertThatOutputMatches(String expectedOutputPath, String actualOutput) throws IOException
    {
        final URL expectedOutputResource = getClass().getResource(expectedOutputPath);
        assertNotNull("Expected output resource must exist: " + expectedOutputPath, expectedOutputResource);

        final String expectedOutput = Resources.toString(expectedOutputResource, OUTPUT_FILE_ENCODING);
        assertEquals(expectedOutputPath, expectedOutput, actualOutput);
    }
}
