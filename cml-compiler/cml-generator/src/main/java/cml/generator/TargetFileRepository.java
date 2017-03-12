package cml.generator;

import cml.language.features.Target;
import cml.templates.TemplateFile;
import cml.templates.TemplateRenderer;
import cml.templates.TemplateRepository;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

interface TargetFileRepository
{
    boolean templatesFoundFor(Target target);
    Set<TargetFile> findTargetFiles(Target target, String fileType, Map<String, Object> args);

    static TargetFileRepository create(TemplateRepository templateRepository, TemplateRenderer templateRenderer)
    {
        return new TargetFileRepositoryImpl(templateRepository, templateRenderer);
    }
}

class TargetFileRepositoryImpl implements TargetFileRepository
{
    private static final String STG_EXT = ".stg";
    private static final String GROUP_FILES = "files" + STG_EXT;
    private static final String FILES_SUFFIX = "Files";
    private static final String FILE_LINE_SEPARATOR = "\\|";
    private static final String TEMPLATE_NAME_SEPARATOR = ":";

    // Collaborators:
    private final TemplateRepository templateRepository;
    private final TemplateRenderer templateRenderer;

    TargetFileRepositoryImpl(TemplateRepository templateRepository, TemplateRenderer templateRenderer)
    {
        this.templateRepository = templateRepository;
        this.templateRenderer = templateRenderer;
    }

    @Override
    public boolean templatesFoundFor(Target target)
    {
        return findTemplatesForTarget(target).isPresent();
    }

    @Override
    public Set<TargetFile> findTargetFiles(
        final Target target,
        final String fileType,
        final Map<String, Object> args)
    {
        final Optional<TemplateFile> fileTemplates = findTemplatesForTarget(target);

        if (fileTemplates.isPresent())
        {
            final String templateName = fileType + FILES_SUFFIX;
            final String files = templateRenderer.renderTemplate(fileTemplates.get(), templateName, args);
            return stream(files.split("\n"))
                .map(line -> line.split(FILE_LINE_SEPARATOR))
                .map(pair -> createTargetFile(pair[1], target.getName(), pair[0]))
                .collect(toSet());
        }
        else
        {
            return emptySet();
        }
    }

    private Optional<TemplateFile> findTemplatesForTarget(Target target)
    {
        return templateRepository.findTemplate(target.getName(), GROUP_FILES);
    }

    private TargetFile createTargetFile(String path, String targetType, String templateName)
    {
        final String[] pair = templateName.split(TEMPLATE_NAME_SEPARATOR);

        if (pair.length == 2)
        {
            targetType = pair[0];
            templateName = pair[1];
        }

        final TargetFile targetFile = new TargetFile(path, templateName);
        final Optional<TemplateFile> templateFile = templateRepository.findTemplate(targetType, templateName + STG_EXT);

        templateFile.ifPresent(targetFile::setTemplateFile);

        return targetFile;
    }

}

