package cml.io;

public class ConsoleFactory
{
    public static Console createConsole()
    {
        return new PlainConsole();
    }
}
