package grinder.ops.map;

import grinder.annotation.Mapping;
import grinder.custom.converter.Converter;
import grinder.custom.converter.ConverterDirection;
import grinder.util.Fields;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultMapHandler implements Converter<Object, Object> {

    static DefaultMapHandler create() {
        return new DefaultMapHandler();
    }

    Map<String, Object> toMap(Object input) {
        return Arrays.stream(input.getClass().getDeclaredFields())
                .filter(field -> !Fields.hasAnnotation(field, Mapping.Ignore.class))
                .peek(field -> field.setAccessible(true))
                .filter(field -> Fields.getValue(field, input).isPresent())
                .collect(Collectors.toMap(Field::getName, field -> getFieldValue(field, input)));
    }

    @SuppressWarnings("unchecked")
    private Object getFieldValue(Field field, Object input) {
        try {
            Mapping mapping = Fields.getAnnotation(field, Mapping.class).orElse(null);

            if (mapping != null && ConverterDirection.shouldConvertToMap(mapping.convertDirection())) {
                return mapping.converter().getDeclaredConstructor().newInstance().convert(
                        Fields.getValue(field, input).orElse(Void.TYPE));
            } else {
                return Fields.getValue(field, input).orElse(Void.TYPE);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

    <BEAN> BEAN fromMap(Map<String, Object> objectMap, Class<BEAN> target) {
        try {
            BEAN output = target.getDeclaredConstructor().newInstance();

            Arrays.stream(target.getDeclaredFields())
                    .filter(field -> !Fields.hasAnnotation(field, Mapping.Ignore.class))
                    .forEach(field -> setField(field, output, objectMap.get(field.getName())));

            return output;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void setField(Field field, Object object, Object value) {
        try {
            field.setAccessible(true);

            Mapping mapping = Fields.getAnnotation(field, Mapping.class).orElse(null);

            if (mapping != null && ConverterDirection.shouldConvertFromMap(mapping.convertDirection())) {
                field.set(object, mapping.converter().getDeclaredConstructor().newInstance().convert(value));
            } else {
                field.set(object, value);
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public Object convert(Object from) {
        return from;
    }
}
