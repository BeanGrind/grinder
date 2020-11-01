package ops.map;

import grinder.ops.map.Mappable;
import ops.AltTestBean;
import ops.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class MapTest {

    @Test
    public void testMap_assertMapped() {
        AltTestBean deepCopy = new AltTestBean();
        deepCopy.setFirstValue("firstValue");

        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldTwo("fieldTwo");
        caller.setIgnoredField("ignore");
        caller.setDeepCopyBean(deepCopy);

        Map<String, Object> mapped = caller.toMap();

        assertEquals("fieldOne", mapped.get("fieldOne"));
        assertEquals("fieldTwo", mapped.get("fieldTwo"));
        assertEquals("firstValue", ((Map ) mapped.get("deepCopyBean")).get("firstValue"));
        assertNull(mapped.get("ignoredField"));
    }

    @Test
    public void testMap_converter_assertMapped() {
        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldTwo("fieldTwo");
        caller.setPositiveNumber(-1L);
        caller.setNegativeNumber(-1L);
        caller.setIgnoredField("ignore");

        Map<String, Object> mapped = caller.toMap();

        assertEquals("fieldOne", mapped.get("fieldOne"));
        assertEquals("fieldTwo", mapped.get("fieldTwo"));
        assertEquals(1L, mapped.get("positiveNumber"));
        assertEquals(1L, mapped.get("negativeNumber"));
        assertNull(mapped.get("ignoredField"));
    }

    @Test
    public void testMap_assertFromMap() {
        AltTestBean deepCopy = new AltTestBean();
        deepCopy.setFirstValue("firstValue");

        AltTestBean basicCopy = new AltTestBean();
        basicCopy.setFirstValue("first");

        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldTwo("fieldTwo");
        caller.setDeepCopyBean(deepCopy);
        caller.setBasicCopyBean(basicCopy);

        Map<String, Object> deepMap = new HashMap<>();
        deepMap.put("firstValue", "firstValue");

        Map<String, Object> map = new HashMap<>();
        map.put("fieldOne", "fieldOne");
        map.put("fieldTwo", "fieldTwo");
        map.put("ignoredField", "ignoredField");
        map.put("deepCopyBean", deepMap);
        map.put("basicCopyBean", basicCopy);

        assertEquals(Mappable.fromMap(map, TestBean.class), caller);
    }

    @Test
    public void testMap_assertFromMap_converted() {
        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldTwo("fieldTwo");
        caller.setPositiveNumber(1L);
        caller.setNegativeNumber(-1L);

        Map<String, Object> map = new HashMap<>();
        map.put("fieldOne", "fieldOne");
        map.put("fieldTwo", "fieldTwo");
        map.put("ignoredField", "ignoredField");
        map.put("positiveNumber", -1L);
        map.put("negativeNumber", -1L);

        assertEquals(Mappable.fromMap(map, TestBean.class), caller);
    }
}
