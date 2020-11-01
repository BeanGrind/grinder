package grinder.ops.map.bindings;

public interface BoundMapper {
    default <TO> TO boundMap(Object from, Class<TO> to) {
        return BoundMapper.map(from, to);
    }

    static <TO> TO map(Object from, Class<TO> to) {
        return DefaultBoundMapper.create().boundMap(from, to);
    }
}
