package ops.validation;

import ops.AltTestBean;
import ops.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ValidationTest {

    @Test
    public void testValidation_Valid() {
        AltTestBean deepCopy = new AltTestBean();
        deepCopy.setFirstValue("firstValue");

        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldTwo("fieldTwo");
        caller.setPatternField("12-345");
        caller.setPositiveNumber(100L);
        caller.setNegativeNumber(-100L);
        caller.setDeepCopyBean(deepCopy);
        caller.setBasicCopyBean(new AltTestBean());

        assertTrue(caller.validate());
    }

    @Test
    public void testValidation_allowedNull_Valid() {
        AltTestBean deepCopy = new AltTestBean();
        deepCopy.setFirstValue("firstValue");

        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldTwo("fieldTwo");
        caller.setPositiveNumber(100L);
        caller.setNegativeNumber(-100L);
        caller.setDeepCopyBean(deepCopy);
        caller.setBasicCopyBean(new AltTestBean());

        caller.setPatternField(null);

        assertTrue(caller.validate());
    }

    @Test
    public void testValidation_missingField_Invalid() {
        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setPatternField("12-345");
        caller.setIgnoredField("ignored");
        caller.setPositiveNumber(100L);

        caller.setFieldTwo(null);

        assertFalse(caller.validate());
    }

    @Test
    public void testValidation_pattern__Invalid() {
        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldTwo("fieldTwo");
        caller.setPositiveNumber(100L);

        caller.setPatternField("badPattern");

        assertFalse(caller.validate());
    }

    @Test
    public void testValidation_positiveNumber_Invalid() {
        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldTwo("fieldTwo");
        caller.setPatternField("12-345");

        caller.setPositiveNumber(-100L);

        assertFalse(caller.validate());
    }

    @Test
    public void testValidation_inValidChild() {
        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldTwo("fieldTwo");
        caller.setPatternField("12-345");
        caller.setPositiveNumber(100L);
        caller.setNegativeNumber(-100L);
        caller.setDeepCopyBean(new AltTestBean());
        caller.setBasicCopyBean(new AltTestBean());

        assertFalse(caller.validate());
    }

}
