package cml.generator;

public class Target
{
    private final String targetType;
    private final String targetDirPath;

    Target(final String targetType, final String targetDirPath)
    {
        this.targetType = targetType;
        this.targetDirPath = targetDirPath;
    }

    public String getTargetType()
    {
        return targetType;
    }

    public String getTargetDirPath()
    {
        return targetDirPath;
    }
}
