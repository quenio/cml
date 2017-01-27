package cml.frontend;

import java.io.File;

public class Launcher
{
    public static void main(String[] args)
    {
        final File sourceDir = new File(args[0]);
        final File targetDir = new File(args[1]);
        final String targetType = args[2];

        System.out.println("source dir = " + sourceDir);
        System.out.println("target dir = " + targetDir);
        System.out.println("target type = " + targetType);
    }
}
