package generic;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theory;
import org.stringtemplate.v4.ST;

import java.util.ArrayList;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GenericTest extends TemplateTest
{
    @DataPoints("trueConditions")
    public static final Object[] trueConditions = { Boolean.TRUE, singletonList("Item") };

    @DataPoints("falseConditions")
    public static final Object[] falseConditions = { Boolean.FALSE, null, emptyList(), new ArrayList() };

    @Override
    protected String getTemplateFileName()
    {
        return "/generic/generic.stg";
    }

    @Theory
    public void newLineIf_true(@FromDataPoints("trueConditions") Object cond)
    {
        testTemplateWithCond("newLineIf", cond, "\n");
    }

    @Theory
    public void newLineIf_false(@FromDataPoints("falseConditions") Object cond)
    {
        testTemplateWithCond("newLineIf", cond, "");
    }

    @Theory
    public void commaIf_true(@FromDataPoints("trueConditions") Object cond)
    {
        testTemplateWithCond("commaIf", cond, ", ");
    }

    @Theory
    public void commaIf_false(@FromDataPoints("falseConditions") Object cond)
    {
        testTemplateWithCond("commaIf", cond, "");
    }

    @Theory
    public void commaIf2_true(@FromDataPoints("trueConditions") Object cond)
    {
        testTemplateWithCond2("commaIf2", cond, cond, ", ");
    }

    @Theory
    public void commaIf2_false(@FromDataPoints("falseConditions") Object cond)
    {
        testTemplateWithCond2("commaIf2", cond, cond,"");
    }

    @Theory
    public void commaIf2_mixed(
        @FromDataPoints("trueConditions") Object trueCond,
        @FromDataPoints("falseConditions") Object falseCond)
    {
        testTemplateWithCond2("commaIf2", trueCond, falseCond,"");
        testTemplateWithCond2("commaIf2", falseCond, trueCond,"");
    }

    private void testTemplateWithCond(String templateName, Object cond, String expectedResult)
    {
        final ST template = getTemplate(templateName);

        template.add("cond", cond);

        final String result = template.render();

        assertThat(result, is(expectedResult));
    }

    private void testTemplateWithCond2(String templateName, Object cond1, Object cond2, String expectedResult)
    {
        final ST template = getTemplate(templateName);

        template.add("cond1", cond1);
        template.add("cond2", cond2);

        final String result = template.render();

        assertThat(result, is(expectedResult));
    }

}
