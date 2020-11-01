package ops.map.bindings.models;

import java.util.Date;
import java.util.Objects;

public class MapToBean {
    private String fieldOne;
    private String fieldTwo;
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapToBean toBean = (MapToBean) o;
        return Objects.equals(fieldOne, toBean.fieldOne) &&
                Objects.equals(fieldTwo, toBean.fieldTwo) &&
                Objects.equals(date, toBean.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldOne, fieldTwo, date);
    }
}
