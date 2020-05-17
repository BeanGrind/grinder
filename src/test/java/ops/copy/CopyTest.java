package ops.copy;

import ops.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

@RunWith(JUnit4.class)
public class CopyTest {

    @Test
    public void testCopy_assertMerged() {
        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldOne("fieldTwo");

        assertEquals(caller, caller.copy());
        assertNotSame(caller, caller.copy());
    }

    @Test
    public void testMerge_assertMergedPriority_ignored() {
        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setIgnoredField("ignored");

        TestBean result = new TestBean();
        result.setFieldOne("fieldOne");

        assertEquals(result, caller.copy());
    }
}
