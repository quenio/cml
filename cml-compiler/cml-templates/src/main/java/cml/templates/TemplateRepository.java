package cml.templates;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public interface TemplateRepository
{
    Optional<TemplateFile> findTemplate(String targetType, String fileName);

    static TemplateRepository create()
    {
        return new TemplateRepositoryImpl();
    }
}

class TemplateRepositoryImpl implements TemplateRepository
{
    @Override
    public Optional<TemplateFile> findTemplate(final String targetType, final String fileName)
    {
        final String path = File.separatorChar + targetType + File.separatorChar + fileName;

        try (InputStream stream = getClass().getResourceAsStream(path))
        {
            return (stream == null) ? Optional.empty() : Optional.of(new TemplateFile(path));
        }
        catch (IOException ignored)
        {
            return Optional.empty();
        }
    }

}

