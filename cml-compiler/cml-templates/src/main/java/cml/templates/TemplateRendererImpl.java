package cml.templates;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import java.net.URL;
import java.util.Map;

class TemplateRendererImpl implements TemplateRenderer
{
    private static final String ENCODING = "UTF-8";

    @Override
    public String renderTemplate(final TemplateFile templateFile, final String templateName, final Map<String, Object> args)
    {
        final URL url = getClass().getResource(templateFile.getPath());
        final STGroupFile groupFile = new STGroupFile(url, ENCODING, '<', '>');
        final ST template = groupFile.getInstanceOf(templateName);

        for (final Map.Entry<String, Object> entry : args.entrySet())
        {
            template.add(entry.getKey(), entry.getValue());
        }

        return template.render();
    }
}
