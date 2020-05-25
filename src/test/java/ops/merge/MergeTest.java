package ops.merge;

import ops.AltTestBean;
import ops.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class MergeTest {

    @Test
    public void testMerge_assertMerged() {
        AltTestBean deepCopy = new AltTestBean();
        deepCopy.setFirstValue("firstValue");

        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setDeepCopyBean(new AltTestBean());

        TestBean priority = new TestBean();
        priority.setFieldTwo("fieldTwo");
        priority.setDeepCopyBean(deepCopy);

        TestBean result = new TestBean();
        result.setFieldOne("fieldOne");
        result.setFieldTwo("fieldTwo");
        result.setDeepCopyBean(deepCopy);

        assertEquals(result, caller.merge(priority));
    }

    @Test
    public void testMerge_assertMergedPriority() {
        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldTwo("two");

        TestBean priority = new TestBean();
        priority.setFieldTwo("fieldTwo");

        TestBean result = new TestBean();
        result.setFieldOne("fieldOne");
        result.setFieldTwo("fieldTwo");

        assertEquals(result, caller.merge(priority));
    }

    @Test
    public void testMerge_assertMergedPriority_ignored() {
        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setIgnoredField("ignored");

        TestBean priority = new TestBean();
        priority.setFieldTwo("fieldTwo");
        priority.setIgnoredField("ignoredTwo");

        TestBean result = new TestBean();
        result.setFieldOne("fieldOne");
        result.setFieldTwo("fieldTwo");
        result.setIgnoredField("ignored");

        assertEquals(result, caller.merge(priority));
    }

    @Test
    public void testMerge_assertNonDeepMerged() {
        AltTestBean priorityAlt = new AltTestBean();
        priorityAlt.setFirstValue("firstValue");

        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setDeepCopyBean(new AltTestBean());
        caller.setBasicCopyBean(priorityAlt);

        TestBean priority = new TestBean();
        priority.setFieldTwo("fieldTwo");
        priority.setDeepCopyBean(priorityAlt);
        priority.setBasicCopyBean(new AltTestBean());

        TestBean result = new TestBean();
        result.setFieldOne("fieldOne");
        result.setFieldTwo("fieldTwo");
        result.setDeepCopyBean(priorityAlt);
        result.setBasicCopyBean(new AltTestBean());

        assertEquals(result, caller.merge(priority));
    }
}
