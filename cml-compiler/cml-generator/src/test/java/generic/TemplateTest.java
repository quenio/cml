package generic;


import cml.templates.NameRenderer;
import cml.templates.TemplateGroupFile;
import org.junit.Before;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import java.util.Collection;
import java.util.Locale;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableCollection;

@RunWith(Theories.class)
public abstract class TemplateTest
{
    protected static final Collection<String> commonNameFormats = unmodifiableCollection(asList(
        "SomeName",
        "someName",
        "some_name",
        "some-name"
    ));

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

    protected static String pascalCase(String name)
    {
        return NameRenderer.pascalCase(Locale.getDefault(), name);
    }

    protected static String camelCase(String name)
    {
        return NameRenderer.camelCase(Locale.getDefault(), name);
    }

    protected static String underscoreCase(String name)
    {
        return NameRenderer.underscoreCase(Locale.getDefault(), name);
    }

    static TemplateGroupFile createTemplateGroupFile(String templatePath)
    {
        return new TemplateGroupFile(format(TEMPLATE_GROUP_PATH, templatePath));
    }
}
