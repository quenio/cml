package cml.generator;

import cml.model.Target;

import java.util.Set;

interface TargetFileRepository
{
    Set<TargetFile> findTargetFiles(Target target, String fileType);
}
