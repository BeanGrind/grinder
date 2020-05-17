package grinder.ops.copy;

public interface Copyable {
    default <BEAN extends Copyable> BEAN copy() {
        return DefaultCopyHandler.create().copy(this);
    }
}
