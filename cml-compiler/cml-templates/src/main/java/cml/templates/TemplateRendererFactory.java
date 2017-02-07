package cml.templates;

public class TemplateRendererFactory
{
    public static TemplateRenderer createTemplateRenderer()
    {
        return new PlainTemplateRenderer();
    }
}
