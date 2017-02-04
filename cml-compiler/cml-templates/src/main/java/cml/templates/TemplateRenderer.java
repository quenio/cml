package cml.templates;

import java.util.Map;

public interface TemplateRenderer
{
    String renderTemplate(TemplateFile templateFile, String templateName, Map<String, Object> args);
}
