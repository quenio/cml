package cml.generator;

import java.io.InputStream;
import java.util.Optional;

public class PlainGenerator implements Generator
{
    private static final String TEMPLATE_FILES = "files.stg";

    @Override
    public Optional<Target> findTarget(String targetType, String targetDirPath)
    {
        final InputStream stream = getClass().getResourceAsStream("/" + targetType + "/" + TEMPLATE_FILES);
        final boolean templatesFound = (stream != null);
        final Target target = templatesFound ? new Target(targetType, targetDirPath) : null;
        return Optional.ofNullable(target);
    }
}
