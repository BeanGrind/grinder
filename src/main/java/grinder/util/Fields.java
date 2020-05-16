package grinder.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Fields {

    public static  <T extends Annotation>  boolean hasAnnotation(Field field, Class<T> annotationClass) {
        return field.getAnnotation(annotationClass) != null;
    }
}
