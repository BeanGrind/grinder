package grinder.ops.copy;

import grinder.annotation.Copy;
import grinder.util.Fields;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

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
        Optional<Copy> copyAnnotation = Fields.getAnnotation(field, Copy.class);
        field.setAccessible(true);

        try {
            if (copyAnnotation.isPresent()) {
                Object value = field.get(copy);

                if (value instanceof Copyable && copyAnnotation.map(Copy::deepCopy).orElse(false)) {
                    field.set(paste, copy(value));
                } else {
                    field.set(paste, field.get(copy));
                }
            } else {
                field.set(paste, field.get(copy));
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error setting " + field.getName() + " on " + paste.getClass().getName());
        }
    }
}
