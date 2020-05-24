package grinder.ops.validation;

import grinder.annotation.Validate;
import grinder.ops.Validator;
import grinder.util.Fields;
import grinder.util.Strings;

import java.lang.reflect.Field;
import java.util.Arrays;

public class DefaultValidationHandler implements Validatable, Validator<Object> {

    static DefaultValidationHandler create() {
        return new DefaultValidationHandler();
    }

    boolean validate(Object toValidate) {
        return Arrays.stream(toValidate.getClass().getDeclaredFields())
                .filter(field -> !Fields.hasAnnotation(field, Validate.Ignore.class))
                .allMatch(field -> isValid(field, toValidate));
    }

    private boolean isValid(Field field, Object object) {
        field.setAccessible(true);

        try {
            Object value = Fields.getValue(field, object).orElse(null);

            Validate validate = Fields.getAnnotation(field, Validate.class).orElse(null);

            if (value == null) {
                return validate != null && validate.allowNull();
            }

            boolean matchPattern = false;
            boolean customValidation = false;

            if (validate != null) {
                matchPattern = validatePattern(validate.pattern(), value.toString());
                customValidation = callCustomValidator(validate.validator(), value);
            }

            return validate == null || (matchPattern && customValidation);
        } catch (Exception ex) {
            throw new RuntimeException("Error getting " + field.getName() + " on " + object.getClass().getName());
        }
    }

    private boolean validatePattern(String pattern, String value) {
        return Strings.isNullOrEmpty(pattern) || value.matches(pattern);
    }

    @SuppressWarnings("unchecked")
    private boolean callCustomValidator(Class<? extends Validator> validator, Object value) {
        try {
            return validator == null || validator.getDeclaredConstructor().newInstance().isValid(value);
        } catch (Exception ex) {
            throw new RuntimeException("No zero arg constructor for " + validator.getName());
        }
    }

    @Override
    public boolean isValid(Object obj) {
        return true;
    }
}
