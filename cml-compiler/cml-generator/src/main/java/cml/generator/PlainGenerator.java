package cml.generator;

import cml.model.Model;

import java.io.InputStream;
import java.util.Optional;

public class PlainGenerator implements Generator
{
    private static final String TEMPLATE_FILES = "files.stg";

    @Override
    public Optional<Target> findTarget(final String targetType, final String targetDirPath)
    {
        final InputStream stream = getClass().getResourceAsStream("/" + targetType + "/" + TEMPLATE_FILES);
        final boolean templatesFound = (stream != null);
        final Target target = templatesFound ? new Target(targetType, targetDirPath) : null;
        return Optional.ofNullable(target);
    }

    @Override
    public void generate(final Model model, final Target target)
    {
        
    }
}
