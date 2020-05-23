package ops.validation;

import ops.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ValidationTest {

    @Test
    public void testCopy_assertCopy() {
        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldTwo("fieldTwo");

        assertTrue(caller.validate());
    }

    @Test
    public void testMerge_assertCopy_ignored() {
        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setIgnoredField("ignored");

        assertFalse(caller.validate());
    }
}
