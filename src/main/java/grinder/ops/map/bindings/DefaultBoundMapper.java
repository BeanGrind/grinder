package grinder.ops.map.bindings;

import grinder.annotation.Binding;
import grinder.util.Classes;
import grinder.util.Fields;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

public class DefaultBoundMapper implements BoundMapper {

    static DefaultBoundMapper create() {
        return new DefaultBoundMapper();
    }

    @Override
    public <TO> TO boundMap(Object from, Class<TO> to) {
        if (isMappingBound(from, to)) {
            return mapResult(from, to);
        } else {
            throw new RuntimeException(from.getClass().getName() + " is not bound to " + to.getName()
                    + ". is " + from.getClass().getName() + " annotated with @Binding?");
        }
    }

    private boolean isMappingBound(Object from, Class<?> clazz) {
        return Classes.getAnnotation(from.getClass(), Binding.class)
                .map(binding -> Arrays.stream(binding.value())
                        .anyMatch(item -> Objects.equals(item.getName(), clazz.getName())))
                .orElse(Boolean.FALSE);
    }

    private <TO> TO mapResult(Object from, Class<TO> to) {
        TO toInstance = Classes.zeroArgConstruct(to);

        Arrays.stream(from.getClass().getDeclaredFields())
                .forEach(field -> mapField(field, from, toInstance));

        return toInstance;
    }

    private <TO> void mapField(Field field, Object from, TO to) {
        field.setAccessible(true);

        if (Fields.hasField(field, to)) {
            try {
                Object value = field.get(from);
                Field target = to.getClass().getDeclaredField(field.getName());
                target.setAccessible(true);

                if (value != null) {
                    target.set(to, value);
                }
            } catch (Exception ex) {
                throw new RuntimeException("Error setting " + field.getName() + " on " + to.getClass().getName());
            }
        }
    }
}
