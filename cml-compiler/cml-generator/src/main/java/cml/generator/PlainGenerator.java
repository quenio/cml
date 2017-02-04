package cml.generator;

import cml.model.Model;

class PlainGenerator implements Generator
{
    private final TargetRepository targetRepository;

    PlainGenerator(final TargetRepository targetRepository)
    {
        this.targetRepository = targetRepository;
    }

    @Override
    public void generate(final Model model, final Target target)
    {

    }
}
