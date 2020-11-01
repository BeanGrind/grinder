package grinder.custom.converter;

public interface Converter<TO, FROM> {
    TO convert(FROM from);
    FROM reverse(TO to);
}
