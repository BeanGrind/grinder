package ops.copy;

import ops.AltTestBean;
import ops.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

@RunWith(JUnit4.class)
public class CopyTest {

    @Test
    public void testCopy_assertCopy() {
        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldTwo("fieldTwo");

        assertEquals(caller, caller.copy());
        assertNotSame(caller, caller.copy());
    }

    @Test
    public void testCopy_deepCopy() {
        AltTestBean deepCopy = new AltTestBean();
        deepCopy.setFirstValue("firstValue");

        AltTestBean basicCopy = new AltTestBean();
        basicCopy.setFirstValue("firstValue");

        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldTwo("fieldTwo");
        caller.setDeepCopyBean(deepCopy);
        caller.setBasicCopyBean(basicCopy);

        TestBean copy = caller.copy();

        assertEquals(caller, copy);
        assertNotSame(caller, copy);
        assertSame(caller.getBasicCopyBean(), copy.getBasicCopyBean());
        assertNotSame(caller.getDeepCopyBean(), copy.getDeepCopyBean());
    }

    @Test
    public void testMerge_assertCopy_ignored() {
        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setIgnoredField("ignored");

        TestBean result = new TestBean();
        result.setFieldOne("fieldOne");

        assertEquals(result, caller.copy());
    }
}
