package cml.io;

import java.io.File;
import java.io.PrintStream;

public class Directory
{
    private final File file;

    public Directory(String path)
    {
        this.file = new File(path);
    }

    public boolean isMissing()
    {
        return !file.exists();
    }

    public void printPath(PrintStream out, String description)
    {
        out.println(description + file.getPath());
    }
}
