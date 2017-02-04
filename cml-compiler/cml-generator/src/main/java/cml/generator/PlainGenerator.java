package cml.generator;

import cml.model.Model;

import java.util.Optional;

class PlainGenerator implements Generator
{
    private static final String GROUP_FILES = "files";

    private final TemplateRepository templateRepository;

    PlainGenerator(final TemplateRepository templateRepository)
    {
        this.templateRepository = templateRepository;
    }

    @Override
    public Optional<Target> findTarget(final String targetType, final String targetDirPath)
    {
        final boolean templatesFound = templateRepository.includesTemplateGroup(targetType, GROUP_FILES);
        final Target target = templatesFound ? new Target(targetType, targetDirPath) : null;
        return Optional.ofNullable(target);
    }

    @Override
    public void generate(final Model model, final Target target)
    {

    }
}
