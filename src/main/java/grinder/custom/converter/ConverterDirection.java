package grinder.custom.converter;

public interface ConverterDirection {
    String FROM_MAP = "fromMap";
    String TO_MAP = "ToMap";
    String BOTH = "both";

    static boolean shouldConvertFromMap(String direction) {
        return FROM_MAP.equals(direction) || BOTH.equals(direction);
    }

    static boolean shouldConvertToMap(String direction) {
        return TO_MAP.equals(direction) || BOTH.equals(direction);
    }
}
