package grinder.ops.validation;

import grinder.annotation.Validate;
import grinder.util.Fields;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

public class DefaultValidationHandler implements Validatable {

    static DefaultValidationHandler create() {
        return new DefaultValidationHandler();
    }

    public boolean validate(Object toValidate) {
        return Arrays.stream(toValidate.getClass().getDeclaredFields())
                .filter(field -> !Fields.hasAnnotation(field, Validate.Ignore.class))
                .allMatch(field -> isValid(field, toValidate));
    }

    private boolean isValid(Field field, Object object) {
        field.setAccessible(true);

        try {
            return Fields.getValue(field, object).isPresent();
        } catch (Exception ex) {
            throw new RuntimeException("Error getting " + field.getName() + " on " + object.getClass().getName());
        }
    }
}
