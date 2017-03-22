package cml.templates;

import org.antlr.runtime.Token;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.misc.Misc;

import java.net.URL;

public class TemplateGroupFile extends STGroupFile
{
    private static final String ENCODING = "UTF-8";
    private static final char START_CHAR = '<';
    private static final char STOP_CHAR = '>';

    public TemplateGroupFile(String fileName)
    {
        super(fileName, ENCODING, START_CHAR, STOP_CHAR);

        registerModelAdaptor(Object.class, new OptionalValueAdaptor());
        registerRenderer(String.class, new NameRenderer());
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
