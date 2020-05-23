package grinder.ops.validation;

import grinder.annotation.Validate;
import grinder.util.Fields;
import grinder.util.Strings;

import java.lang.reflect.Field;
import java.util.Arrays;

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
            Object value = Fields.getValue(field, object).orElse(null);

            if (value == null) {
                return false;
            }

            Validate validate = Fields.getAnnotation(field, Validate.class).orElse(null);
            boolean matchPattern = false;

            if (validate != null) {
                matchPattern = validatePattern(validate.pattern(), value.toString());
            }

            return validate == null || matchPattern;
        } catch (Exception ex) {
            throw new RuntimeException("Error getting " + field.getName() + " on " + object.getClass().getName());
        }
    }

    private boolean validatePattern(String pattern, String value) {
        return Strings.isNullOrEmpty(pattern) || value.matches(pattern);
    }
}
