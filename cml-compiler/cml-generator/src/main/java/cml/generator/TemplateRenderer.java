package cml.generator;

interface TemplateRenderer
{
    void loadTemplateGroup(String groupName);

    void renderTargetFile(String templateName, String targetPath);
}
