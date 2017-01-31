package cml.frontend;

import cml.generator.Target;
import cml.io.Directory;

import java.io.PrintStream;

class Compiler
{
    private static final int SUCCESS = 0;
    private static final int FAILURE = 1;

    private final PrintStream out;

    Compiler(PrintStream out)
    {
        this.out = out;
    }

    int compile(final Directory sourceDir, final Target target)
    {
       if (sourceDir.isMissing())
       {
           sourceDir.printPath(out, "Source dir missing: ");
           return FAILURE;
       }

       if (target.isMissingTemplates())
       {
           target.printTargetType(out, "Unknown target type: ");
           return FAILURE;
       }

        sourceDir.printPath(out, "source dir = ");
        target.printTargetDirPath(out, "target dir = ");
        target.printTargetType(out, "target type = ");

        return SUCCESS;
    }
}
