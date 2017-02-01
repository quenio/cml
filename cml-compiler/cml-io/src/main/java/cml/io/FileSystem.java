package cml.io;

import java.util.Optional;

public interface FileSystem
{
    Optional<Directory> findDirectory(String path);
    Optional<SourceFile> findSourceFile(Directory directory, String name);
}
