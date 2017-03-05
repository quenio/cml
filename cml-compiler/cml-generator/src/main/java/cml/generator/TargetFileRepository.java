package cml.generator;

import cml.language.features.Target;

import java.util.Map;
import java.util.Set;

interface TargetFileRepository
{
    Set<TargetFile> findTargetFiles(Target target, String fileType, Map<String, Object> args);
}
