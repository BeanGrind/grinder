package grinder.ops.validation;

public interface Validatable {
    default boolean validate() {
        return DefaultValidationHandler.create().validate(this);
    }
}
