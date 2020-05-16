package ops.merge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class MergeTest {

    @Test
    public void testMerge_assertMerged() {
        MergeBean caller = new MergeBean();
        caller.setFieldOne("fieldOne");

        MergeBean priority = new MergeBean();
        priority.setFieldTwo("fieldTwo");

        MergeBean result = new MergeBean();
        result.setFieldOne("fieldOne");
        result.setFieldTwo("fieldTwo");

        assertEquals(result, caller.merge(priority));
    }

    @Test
    public void testMerge_assertMergedPriority() {
        MergeBean caller = new MergeBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldTwo("two");

        MergeBean priority = new MergeBean();
        priority.setFieldTwo("fieldTwo");

        MergeBean result = new MergeBean();
        result.setFieldOne("fieldOne");
        result.setFieldTwo("fieldTwo");

        assertEquals(result, caller.merge(priority));
    }

    @Test
    public void testMerge_assertMergedPriority_ignored() {
        MergeBean caller = new MergeBean();
        caller.setFieldOne("fieldOne");
        caller.setIgnoredField("ignored");

        MergeBean priority = new MergeBean();
        priority.setFieldTwo("fieldTwo");
        priority.setIgnoredField("ignoredTwo");

        MergeBean result = new MergeBean();
        result.setFieldOne("fieldOne");
        result.setFieldTwo("fieldTwo");
        result.setIgnoredField("ignored");

        assertEquals(result, caller.merge(priority));
    }
}
