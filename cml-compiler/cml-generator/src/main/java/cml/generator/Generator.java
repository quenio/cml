package cml.generator;

import cml.io.Console;
import cml.io.FileSystem;
import cml.language.features.Model;
import cml.templates.TemplateRenderer;
import cml.templates.TemplateRepository;

public interface Generator
{
    int generate(Model model, final String targetType, final String targetDirPath);

    static Generator create(final Console console, final FileSystem fileSystem)
    {
        final TemplateRepository templateRepository = TemplateRepository.create();
        final TemplateRenderer templateRenderer = TemplateRenderer.create();
        final TargetFileRepository targetFileRepository = new TargetFileRepositoryImpl(templateRepository, templateRenderer);
        return new GeneratorImpl(console, fileSystem, targetFileRepository, templateRenderer);
    }
}
