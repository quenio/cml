package cml.generator;

import cml.model.Target;
import cml.templates.TemplateFile;
import cml.templates.TemplateRenderer;
import cml.templates.TemplateRepository;

import java.util.*;

import static java.util.Arrays.stream;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

class PlainTargetFileRepository implements TargetFileRepository
{
    private static final String GROUP_FILES = "files.stg";
    private static final String FILES_SUFFIX = "Files";

    // Collaborators:
    private final TemplateRepository templateRepository;
    private final TemplateRenderer templateRenderer;

    PlainTargetFileRepository(final TemplateRepository templateRepository, final TemplateRenderer templateRenderer)
    {
        this.templateRepository = templateRepository;
        this.templateRenderer = templateRenderer;
    }

    @Override
    public Set<TargetFile> findTargetFiles(final Target target, final String fileType)
    {
        final Optional<TemplateFile> fileTemplates = templateRepository.findTemplate(target.getTargetType(), GROUP_FILES);

        if (fileTemplates.isPresent())
        {
            final Map<String, Object> args = new HashMap<>();
            args.put(target.getTargetType(), target);

            final String templateName = fileType + FILES_SUFFIX;
            final String files = templateRenderer.renderTemplate(fileTemplates.get(), templateName, args);
            return stream(files.split("\n"))
                .map(line -> line.split("\\|"))
                .map(pair -> createTargetFile(pair[1], target.getTargetType(), pair[0]))
                .collect(toSet());
        }
        else
        {
            return emptySet();
        }
    }

    private TargetFile createTargetFile(final String path, final String targetType, final String templateName)
    {
        final TargetFile targetFile = new TargetFile(path);
        final Optional<TemplateFile> templateFile = templateRepository.findTemplate(targetType, templateName);

        templateFile.ifPresent(targetFile::setTemplateFile);

        return targetFile;
    }

}
