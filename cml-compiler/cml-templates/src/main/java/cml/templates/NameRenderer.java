package cml.templates;

import org.stringtemplate.v4.StringRenderer;

import java.util.Locale;

public class NameRenderer extends StringRenderer
{
    private static final String PASCAL_CASE = "pascal-case";
    private static final String CAMEL_CASE = "camel-case";

    @Override
    public String toString(Object o, String formatString, Locale locale)
    {
        if (PASCAL_CASE.equals(formatString))
        {
            final String str = o.toString();
            return str.substring(0, 1).toUpperCase(locale) + str.substring(1);
        }
        else if (CAMEL_CASE.equals(formatString))
        {
            final String str = o.toString();
            return str.substring(0, 1).toLowerCase(locale) + str.substring(1);
        }
        else
        {
            return super.toString(o, formatString, locale);
        }
    }
}
