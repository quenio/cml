package cml.generator;

import cml.io.Directory;

import java.io.PrintStream;

public class Target
{
    private static final String TEMPLATE_FILES = "files.stg";
    
    private final Directory targetDir;
    private final String targetType;

    public Target(final Directory targetDir, final String targetType)
    {
        this.targetDir = targetDir;
        this.targetType = targetType;
    }

    public boolean isMissingTemplates()
    {
        return getClass().getResourceAsStream("/" + targetType + "/" + TEMPLATE_FILES) == null;
    }

    public void printTargetDirPath(final PrintStream out, final String description)
    {
        targetDir.printPath(out, description);
    }

    public void printTargetType(final PrintStream out, final String description)
    {
        out.println(description + targetType);
    }
}
