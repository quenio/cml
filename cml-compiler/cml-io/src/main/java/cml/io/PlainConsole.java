package cml.io;

class PlainConsole implements Console
{
    @Override
    public void println(final String message, final Object... args)
    {
        System.out.println(String.format(message, args));
    }
}
