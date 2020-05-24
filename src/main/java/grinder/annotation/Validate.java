package grinder.annotation;

import grinder.custom.validator.Validator;
import grinder.ops.validation.DefaultValidationHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Validate {
    boolean allowNull() default false;

    String pattern() default "";

    Class<? extends Validator> validator() default DefaultValidationHandler.class;

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Ignore {

    }
}
