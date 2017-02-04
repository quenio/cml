package cml.templates;

import java.io.InputStream;

class PlainTemplateRepository implements TemplateRepository
{
    private static final String GROUP_EXT = ".stg";

    @Override
    public boolean includesTemplateGroup(final String targetType, final String groupName)
    {
        final String resourcePath = "/" + targetType + "/" + groupName + GROUP_EXT;
        final InputStream stream = getClass().getResourceAsStream(resourcePath);
        return stream != null;
    }
}
