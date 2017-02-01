package cml.io;

public class PlainConsole implements Console
{
    @Override
    public void println(String message, Object... args)
    {
        System.out.println(String.format(message, args));
    }
}
