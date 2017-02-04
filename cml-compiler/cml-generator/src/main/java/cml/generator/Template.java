package cml.generator;

class Template
{
    private final String templateName;

    public Template(final String templateName, final String path)
    {
        this.templateName = templateName;
    }

    public String getTemplateName()
    {
        return templateName;
    }
}
