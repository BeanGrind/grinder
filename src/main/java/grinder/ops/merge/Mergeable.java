package grinder.ops.merge;

public interface Mergeable {

    default <BEAN extends Mergeable> BEAN merge(BEAN bean) {
        return DefaultMergeHandler.create().merge(this, bean);
    }
}
