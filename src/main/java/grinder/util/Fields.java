package grinder.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Optional;

public class Fields {

    public static  <T extends Annotation>  boolean hasAnnotation(Field field, Class<T> annotationClass) {
        return getAnnotation(field, annotationClass).isPresent();
    }

    public static <T extends Annotation> Optional<T> getAnnotation(Field field, Class<T> annotationClass) {
        return Optional.ofNullable(field.getDeclaredAnnotation(annotationClass));
    }

    public static Optional<Object> getValue(Field field, Object input) {
        try {
            return Optional.ofNullable(field.get(input));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
