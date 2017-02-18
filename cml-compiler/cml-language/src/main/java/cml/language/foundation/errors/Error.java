package cml.language.foundation.errors;

import cml.language.foundation.elements.ModelElement;

import java.util.HashSet;
import java.util.Set;

class Error
{
    private final String code;
    private final ModelElement element;
    private final Set<ModelElement> participants = new HashSet<>();

    Error(String code, ModelElement element)
    {
        this.code = code;
        this.element = element;
    }

    boolean addParticipants(Set<? extends ModelElement> participants)
    {
        return this.participants.addAll(participants);
    }
}
