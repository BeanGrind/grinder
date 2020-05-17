package grinder.ops.merge;

public interface Mergeable {

    default <BEAN extends Mergeable> BEAN merge(Object bean) {
        return DefaultMergeHandler.create().merge(this, bean);
    }
}
