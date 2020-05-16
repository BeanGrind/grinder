package grinder.ops.merge;

import grinder.annotation.Merge;
import grinder.util.Fields;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

class DefaultMergeHandler {

    static DefaultMergeHandler create() {
        return new DefaultMergeHandler();
    }

    @SuppressWarnings("unchecked")
    <BEAN extends Mergeable> BEAN merge(Object caller, BEAN priority) {
        Arrays.stream(priority.getClass().getDeclaredFields())
                .filter(field -> !Fields.hasAnnotation(field, Merge.Ignore.class))
                .forEach(field -> mergeField(field, caller, priority));

        return (BEAN) caller;
    }

    private void mergeField(Field field, Object caller, Object priority) {
        field.setAccessible(true);

        try {
            Optional<Object> priorityValue = Optional.ofNullable(field.get(priority));

            if (priorityValue.isPresent()) {
                field.set(caller, priorityValue.get());
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error setting " + field.getName() + " on " + caller.getClass().getName());
        }
    }
}
