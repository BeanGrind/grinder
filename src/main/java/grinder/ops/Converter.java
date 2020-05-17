package grinder.ops;

@FunctionalInterface
public interface Converter<TO, FROM> {
    TO convert(FROM from);
}
