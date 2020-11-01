package ops.map.bindings.models;

import java.util.Objects;

public class MapToBean {
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
        MapToBean mapToBean = (MapToBean) o;
        return Objects.equals(fieldOne, mapToBean.fieldOne) &&
                Objects.equals(fieldTwo, mapToBean.fieldTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldOne, fieldTwo);
    }
}
