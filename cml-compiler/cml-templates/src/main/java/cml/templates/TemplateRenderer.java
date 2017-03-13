package cml.templates;

import cml.io.Console;
import org.antlr.runtime.Token;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.misc.Misc;

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
    private final Console console;

    TemplateRendererImpl(Console console)
    {
        this.console = console;
    }

    @Override
    public String renderTemplate(final TemplateFile templateFile, final String templateName, final Map<String, Object> args)
    {
        final STGroupFile groupFile = new TemplateGroupFile(templateFile.getPath());
        final ST template = groupFile.getInstanceOf(templateName);

        if (template == null)
        {
            printErrorMessage(templateFile, templateName);
            return "";
        }

        for (final Entry<String, Object> entry : args.entrySet())
        {
            template.add(entry.getKey(), entry.getValue());
        }

        groupFile.registerModelAdaptor(Object.class, new OptionalValueAdaptor());
        groupFile.registerRenderer(String.class, new NameRenderer());

        return template.render();
    }

    private void printErrorMessage(TemplateFile templateFile, String templateName)
    {
        console.println(
            "Unable to load template named '%s' from file: %s",
            templateName, templateFile.getPath());
    }
}

class TemplateGroupFile extends STGroupFile
{
    private static final String ENCODING = "UTF-8";
    private static final char START_CHAR = '<';
    private static final char STOP_CHAR = '>';

    TemplateGroupFile(String fileName)
    {
        super(fileName, ENCODING, START_CHAR, STOP_CHAR);
    }

    @Override
    public URL getURL(String fileName)
    {
        return getClass().getResource(fileName);
    }

    @Override
    public void importTemplates(Token fileNameToken)
    {
        final String importFileName = Misc.strip(fileNameToken.getText(), 1);
        final STGroup g = new TemplateGroupFile(importFileName);

        g.setListener(getListener());

        importTemplates(g, true);
    }
}

