package cml.io;

import java.io.File;
import java.util.Optional;

public class PlainFileSystem implements FileSystem
{
    @Override
    public Optional<Directory> findDirectory(String path)
    {
        final File file = new File(path);
        final Directory directory = file.isDirectory() ? new Directory(path) : null;
        return Optional.ofNullable(directory);
    }
}
