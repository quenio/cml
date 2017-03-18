package generic;


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

    protected void assertThatOutputMatches(String expectedOutputPath, String actualOutput) throws IOException
    {
        final URL expectedOutputResource = getClass().getResource(expectedOutputPath);
        assertNotNull("Expected output resource must exist: " + expectedOutputPath, expectedOutputResource);

        final String expectedOutput = Resources.toString(expectedOutputResource, OUTPUT_FILE_ENCODING);
        assertEquals(expectedOutputPath, expectedOutput, actualOutput);
    }
}
