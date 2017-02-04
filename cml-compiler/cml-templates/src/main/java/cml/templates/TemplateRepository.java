package cml.templates;

public interface TemplateRepository
{
    boolean includesTemplateGroup(String targetType, String groupName);
}
