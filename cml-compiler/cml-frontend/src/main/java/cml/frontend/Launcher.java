package cml.frontend;

import cml.generator.Target;
import cml.io.Directory;

public class Launcher
{
    public static void main(String[] args)
    {
        final Compiler compiler = new Compiler(System.out);

        final Directory sourceDir = new Directory(args[0]);
        final Directory targetDir = new Directory(args[1]);
        final String targetType = args[2];
        final Target target = new Target(targetDir, targetType);

        final int exitCode = compiler.compile(sourceDir, target);

        System.exit(exitCode);
    }
}
