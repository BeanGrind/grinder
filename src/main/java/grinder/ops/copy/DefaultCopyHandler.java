package grinder.ops.copy;

import grinder.annotation.Copy;
import grinder.util.Fields;

import java.lang.reflect.Field;
import java.util.Arrays;

class DefaultCopyHandler {

    static DefaultCopyHandler create() {
        return new DefaultCopyHandler();
    }

    @SuppressWarnings("unchecked")
    <BEAN extends Copyable> BEAN copy(Object copy) {
        try {
            Object paste = copy.getClass().getDeclaredConstructor().newInstance();

            Arrays.stream(copy.getClass().getDeclaredFields())
                    .filter(field -> !Fields.hasAnnotation(field, Copy.Ignore.class))
                    .forEach(field -> copyField(field, copy, paste));

            return (BEAN) paste;
        } catch (Exception ex) {
            return null;
        }
    }

    private void copyField(Field field, Object copy, Object paste) {
        field.setAccessible(true);

        try {
            field.set(paste, field.get(copy));
        } catch (Exception ex) {
            throw new RuntimeException("Error setting " + field.getName() + " on " + paste.getClass().getName());
        }
    }
}
