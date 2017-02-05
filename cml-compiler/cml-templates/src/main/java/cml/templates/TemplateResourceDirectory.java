package cml.templates;

import java.io.InputStream;
import java.util.Optional;

class TemplateResourceDirectory implements TemplateRepository
{
    @Override
    public Optional<TemplateFile> findTemplate(final String targetType, final String fileName)
    {
        final String path = "/" + targetType + "/" + fileName;
        final InputStream stream = getClass().getResourceAsStream(path);
        return stream == null ? Optional.empty() : Optional.of(new TemplateFile(path));
    }

}
