package ops.map.bindings;

import grinder.ops.map.bindings.BoundMapper;
import ops.map.bindings.models.MapFromBean;
import ops.map.bindings.models.MapToBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BoundMapperTest {

    private static final Date NOW = new Date();

    @Test
    public void basicMappingTest() {
        MapFromBean fromBean = new MapFromBean();
        fromBean.setFieldOne("fieldOne");
        fromBean.setFieldTwo("fieldTwo");

        MapToBean testBean = new MapToBean();
        testBean.setFieldOne("fieldOne");
        testBean.setFieldTwo("fieldTwo");

        MapToBean toBean = BoundMapper.map(fromBean, MapToBean.class);

        assertEquals("Mapped bean does not match expected", testBean, toBean);
    }

    @Test
    public void conversionTest() {
        MapFromBean fromBean = new MapFromBean();
        fromBean.setDate(NOW.getTime());

        MapToBean testBean = new MapToBean();
        testBean.setDate(NOW);

        MapToBean toBean = BoundMapper.map(fromBean, MapToBean.class);

        assertEquals("Mapped bean does not match expected", testBean, toBean);
    }
}
