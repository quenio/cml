package cml.generator;

import cml.templates.Template;
import cml.templates.TemplateRepository;

import java.util.Optional;

public class PlainTargetRepository implements TargetRepository
{
    private static final String GROUP_FILES = "files";

    private final TemplateRepository templateRepository;

    PlainTargetRepository(final TemplateRepository templateRepository)
    {
        this.templateRepository = templateRepository;
    }

    @Override
    public Optional<Target> createTarget(final String targetType, final String targetDirPath)
    {
        final Optional<Template> filesTemplate = templateRepository.findTemplate(targetType, GROUP_FILES);
        final Target target = filesTemplate.isPresent() ? new Target(targetType, targetDirPath) : null;
        return Optional.ofNullable(target);
    }

}
