package grinder.custom.converter;

@FunctionalInterface
public interface Converter<TO, FROM> {
    TO convert(FROM from);
}
