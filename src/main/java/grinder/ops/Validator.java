package grinder.ops;

@FunctionalInterface
public interface Validator<T> {
    boolean isValid(T value);
}
