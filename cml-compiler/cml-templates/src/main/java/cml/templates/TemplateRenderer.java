package cml.templates;

import cml.io.Console;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

public interface TemplateRenderer
{
    String renderTemplate(TemplateFile templateFile, String templateName, Map<String, Object> args);

    static TemplateRenderer create(Console console)
    {
        return new TemplateRendererImpl(console);
    }
}

class TemplateRendererImpl implements TemplateRenderer
{
    private static final String ENCODING = "UTF-8";
    private static final char START_CHAR = '<';
    private static final char STOP_CHAR = '>';

    private final Console console;

    TemplateRendererImpl(Console console)
    {
        this.console = console;
    }

    @Override
    public String renderTemplate(final TemplateFile templateFile, final String templateName, final Map<String, Object> args)
    {
        final URL url = getClass().getResource(templateFile.getPath());
        final STGroupFile groupFile = new STGroupFile(url, ENCODING, START_CHAR, STOP_CHAR);
        final ST template = groupFile.getInstanceOf(templateName);

        if (template == null)
        {
            console.println(
                "Unable to load template named '%s' from file: %s",
                templateName, templateFile.getPath());
            return "";
        }

        for (final Entry<String, Object> entry : args.entrySet())
        {
            template.add(entry.getKey(), entry.getValue());
        }

        return template.render();
    }
}

