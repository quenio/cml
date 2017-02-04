package cml.generator;

class TemplateFile
{
    private final String groupName;
    private final String path;

    public TemplateFile(final String groupName, final String path)
    {
        this.groupName = groupName;
        this.path = path;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public String getPath()
    {
        return path;
    }
}
