package cml.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import static org.apache.commons.io.FileUtils.forceMkdir;

class PlainFileSystem implements FileSystem
{
    private static final String EXCEPTION_MESSAGE = "Unexpected exception. File should have been created: ";

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

    @Override
    public void createFile(final String path, final String content)
    {
        try
        {
            final File file = new File(path);

            if (!file.getParentFile().exists())
            {
                forceMkdir(file.getParentFile());
            }

            try (final PrintWriter output = new PrintWriter(file))
            {
                output.print(content);
            }
        }
        catch (final IOException exception)
        {
            throw new RuntimeException(EXCEPTION_MESSAGE + path, exception);
        }
    }
}
