package grinder.ops.map;

import java.util.Map;

public interface Mappable {
    default Map<String, Object> toMap() {
        return DefaultMapHandler.create().toMap(this);
    }

    static <BEAN> BEAN fromMap(Map<String, Object> objectMap, Class<BEAN> target) {
        return DefaultMapHandler.create().fromMap(objectMap, target);
    }
}
