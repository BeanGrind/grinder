package grinder.annotation;

import grinder.custom.converter.Converter;
import grinder.custom.converter.ConverterDirection;
import grinder.ops.map.DefaultMapHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Mapping {
    String convertDirection() default ConverterDirection.BOTH;

    Class<? extends Converter> converter() default DefaultMapHandler.class;

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Ignore {

    }
}
