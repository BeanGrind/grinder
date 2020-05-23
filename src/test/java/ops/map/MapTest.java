package ops.map;

import grinder.ops.map.Mappable;
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
    public void testCopy_assertMapped() {
        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldTwo("fieldTwo");
        caller.setIgnoredField("ignore");

        Map<String, Object> mapped = caller.toMap();

        assertEquals("fieldOne", mapped.get("fieldOne"));
        assertEquals("fieldTwo", mapped.get("fieldTwo"));
        assertNull(mapped.get("ignoredField"));
    }

    @Test
    public void testMerge_assertFromMap() {
        TestBean caller = new TestBean();
        caller.setFieldOne("fieldOne");
        caller.setFieldTwo("fieldTwo");

        Map<String, Object> map = new HashMap<>();
        map.put("fieldOne", "fieldOne");
        map.put("fieldTwo", "fieldTwo");
        map.put("ignoredField", "ignoredField");

        assertEquals(Mappable.fromMap(map, TestBean.class), caller);
    }
}
