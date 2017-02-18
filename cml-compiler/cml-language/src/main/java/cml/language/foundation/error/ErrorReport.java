package cml.language.foundation.error;

import cml.language.foundation.elements.NamedElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class ErrorReport
{
    private static final String NAME_CONFLICT = "name-conflict";
    private static final String UNKNOWN_TYPE = "unknown-type";

    private final List<Error> errors = new ArrayList<>();

    void reportNameConflict(NamedElement element, Set<NamedElement> conflictingElements)
    {
        final Error error = new Error(NAME_CONFLICT, element);

        error.addParticipants(conflictingElements);

        errors.add(error);
    }

    // TODO Change NamedElement to Type.
    void reportUnknownType(NamedElement type)
    {
        errors.add(new Error(UNKNOWN_TYPE, type));
    }
}
