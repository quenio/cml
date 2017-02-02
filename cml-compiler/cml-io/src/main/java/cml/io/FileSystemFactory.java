package cml.io;

public class FileSystemFactory
{
    public static FileSystem createFileSystem()
    {
        return new PlainFileSystem();
    }
}
