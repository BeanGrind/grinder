package grinder.custom.validator;

@FunctionalInterface
public interface Validator<T> {
    boolean isValid(T value);
}
