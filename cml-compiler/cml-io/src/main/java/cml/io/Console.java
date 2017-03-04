package cml.io;

public interface Console
{
    void println(String message, Object... args);

    static Console create()
    {
        return new ConsoleImpl();
    }
}
