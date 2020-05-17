package grinder.ops.map;

import grinder.annotation.Mapping;
import grinder.util.Fields;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

class DefaultMapHandler {

    static DefaultMapHandler create() {
        return new DefaultMapHandler();
    }

    Map<String, Object> toMap(Object input) {
        return Arrays.stream(input.getClass().getDeclaredFields())
                .filter(field -> !Fields.hasAnnotation(field, Mapping.Ignore.class))
                .peek(field -> field.setAccessible(true))
                .filter(field -> Fields.getValue(field, input).isPresent())
                .collect(Collectors.toMap(Field::getName, field -> Fields.getValue(field, input).orElse(Void.TYPE)));
    }

    <BEAN> BEAN fromMap(Map<String, Object> objectMap, Class<BEAN> target) {
        try {
            BEAN output = target.getDeclaredConstructor().newInstance();

            Arrays.stream(target.getDeclaredFields())
                    .filter(field -> !Fields.hasAnnotation(field, Mapping.Ignore.class))
                    .forEach(field -> setField(field, output, objectMap.get(field.getName())));

            return output;
        } catch (Exception e) {
            return null;
        }
    }

    private void setField(Field field, Object object, Object value) {
        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
