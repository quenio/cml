package cml.generator;

import cml.model.Model;

import java.util.Optional;

class PlainGenerator implements Generator
{
    private static final String GROUP_FILES = "files";

    private final TemplateDirectory templateDirectory;

    PlainGenerator(final TemplateDirectory templateDirectory)
    {
        this.templateDirectory = templateDirectory;
    }

    @Override
    public Optional<Target> findTarget(final String targetType, final String targetDirPath)
    {
        final boolean templatesFound = templateDirectory.includesTemplateGroup(targetType, GROUP_FILES);
        final Target target = templatesFound ? new Target(targetType, targetDirPath) : null;
        return Optional.ofNullable(target);
    }

    @Override
    public void generate(final Model model, final Target target)
    {
        
    }
}
