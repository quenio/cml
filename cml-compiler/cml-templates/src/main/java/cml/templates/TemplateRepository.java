package cml.templates;

import java.util.Optional;

public interface TemplateRepository
{
    Optional<TemplateFile> findTemplate(String targetType, String fileName);
}
