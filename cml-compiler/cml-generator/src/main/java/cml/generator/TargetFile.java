package cml.generator;

import cml.templates.TemplateFile;

import java.util.Optional;

class TargetFile
{
    // Attributes:
    private final String path;

    // Associations:
    private TemplateFile templateFile;

    TargetFile(final String path)
    {
        this.path = path;
    }

    // Attributes:

    public String getPath()
    {
        return path;
    }

    // Associations:

    Optional<TemplateFile> getTemplateFile()
    {
        return Optional.of(templateFile);
    }

    void setTemplateFile(TemplateFile templateFile)
    {
        this.templateFile = templateFile;
    }
}
