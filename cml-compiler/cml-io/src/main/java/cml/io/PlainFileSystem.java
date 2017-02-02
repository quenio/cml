package cml.io;

import java.io.File;
import java.util.Optional;

class PlainFileSystem implements FileSystem
{
    @Override
    public Optional<Directory> findDirectory(final String path)
    {
        final File file = new File(path);
        final Directory directory = file.isDirectory() ? new Directory(path) : null;
        return Optional.ofNullable(directory);
    }

    @Override
    public Optional<SourceFile> findSourceFile(final Directory directory, final String name)
    {
        final File file = new File(new File(directory.getPath()), name);
        final SourceFile sourceFile = file.isFile() ? new SourceFile(file.getPath()) : null;
        return Optional.ofNullable(sourceFile);
    }
}
