package patterns;

import cml.language.foundation.Type;
import generic.TemplateTest;
import org.junit.Test;

public class TypeTest extends TemplateTest
{
    @Override
    protected String getTemplatePath()
    {
        return "patterns/type";
    }

    @Test
    public void typeName()
    {
        for (String name : commonNameFormats)
        {
            testTypeName(name);
        }
    }

    private void testTypeName(String name)
    {
        testTemplateWithNamedElement("typeName", Type.create(name, null), pascalCase(name));
    }
}
