package ops.map.bindings.models;

import grinder.annotation.Binding;
import grinder.annotation.ConvertWith;
import grinder.custom.converter.converters.LongToDateConverter;

import java.util.Objects;

@Binding(MapToBean.class)
public class MapFromBean {
    private String fieldOne;
    private String fieldTwo;

    @ConvertWith(LongToDateConverter.class)
    private Long date;

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

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapFromBean fromBean = (MapFromBean) o;
        return Objects.equals(fieldOne, fromBean.fieldOne) &&
                Objects.equals(fieldTwo, fromBean.fieldTwo) &&
                Objects.equals(date, fromBean.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldOne, fieldTwo, date);
    }
}
