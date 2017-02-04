package cml.templates;

import java.io.InputStream;
import java.util.Optional;

class TemplateResourceDirectory implements TemplateRepository
{
    private static final String GROUP_EXT = ".stg";

    @Override
    public Optional<Template> findTemplate(final String targetType, final String templateName)
    {
        final String resourcePath = "/" + targetType + "/" + templateName + GROUP_EXT;
        final InputStream stream = getClass().getResourceAsStream(resourcePath);
        return stream == null ? Optional.empty() : Optional.of(new Template(templateName));
    }

}
