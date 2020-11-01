package ops.map.bindings.models;

import grinder.annotation.Binding;

import java.util.Objects;

@Binding(MapToBean.class)
public class MapFromBean {
    private String fieldOne;
    private String fieldTwo;

    public String getFieldOne() {
        return fieldOne;
    }

    public void setFieldOne(String fieldOne) {
        this.fieldOne = fieldOne;
    }

    public String getFieldTwo() {
        return fieldTwo;
    }

    public void setFieldTwo(String fieldTwo) {
        this.fieldTwo = fieldTwo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapFromBean fromBean = (MapFromBean) o;
        return Objects.equals(fieldOne, fromBean.fieldOne) &&
                Objects.equals(fieldTwo, fromBean.fieldTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldOne, fieldTwo);
    }
}
