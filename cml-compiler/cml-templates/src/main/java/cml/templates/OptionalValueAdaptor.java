package cml.templates;

import org.stringtemplate.v4.Interpreter;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.misc.ObjectModelAdaptor;
import org.stringtemplate.v4.misc.STNoSuchPropertyException;

import java.util.Optional;

public class OptionalValueAdaptor extends ObjectModelAdaptor
{
    @Override
    public synchronized Object getProperty(Interpreter interp, ST self, Object o, Object property, String propertyName)
        throws STNoSuchPropertyException
    {
        final Object value = super.getProperty(interp, self, o, property, propertyName);

        if (value instanceof Optional)
        {
            @SuppressWarnings("rawtypes")
            final Optional optionalValue = (Optional)value;

            //noinspection unchecked
            return optionalValue.orElse(null);
        }
        else
        {
            return value;
        }
    }
}
