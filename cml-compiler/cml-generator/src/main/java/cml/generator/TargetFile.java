package cml.generator;

public class TargetFile
{
    private final String templateName;
    private final String filePath;

    public TargetFile(final String templateName, final String filePath)
    {
        this.templateName = templateName;
        this.filePath = filePath;
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public String getFilePath()
    {
        return filePath;
    }
}
