package cml.generator;

public class TargetFile
{
    private final String groupName;
    private final String path;

    public TargetFile(final String groupName, final String path)
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
