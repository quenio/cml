package cml.templates;

import java.util.Optional;

public interface TemplateRepository
{
    Optional<Template> findTemplate(String targetType, String templateName);
}
