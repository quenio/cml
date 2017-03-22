package mcml.compiler;

import mcml.language.ModelElement;
import mcml.language.PropertyList;
import mcml.language.Scope;

public class Launcher
{
    public static void main(final String[] args)
    {
        System.out.println("Mini-CML Compiler");
        System.out.println();
        System.out.println("Classes:");
        System.out.println("- " + ModelElement.class.getName());
        System.out.println("- " + Scope.class.getName());
        System.out.println("- " + PropertyList.class.getName());
        System.out.println();
    }
}
