package grinder.util;

import java.lang.annotation.Annotation;
import java.util.Optional;

public class Classes {

    public static <T> T zeroArgConstruct(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static  <T extends Annotation>  boolean hasAnnotation(Class<?> clazz, Class<T> annotationClass) {
        return getAnnotation(clazz, annotationClass).isPresent();
    }

    public static <T extends Annotation> Optional<T> getAnnotation(Class<?> clazz, Class<T> annotationClass) {
        return Optional.ofNullable(clazz.getDeclaredAnnotation(annotationClass));
    }

}
