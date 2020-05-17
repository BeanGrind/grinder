package ops;

import grinder.annotation.Copy;
import grinder.annotation.Merge;
import grinder.ops.copy.Copyable;
import grinder.ops.merge.Mergeable;

import java.util.Objects;

public class TestBean implements Mergeable, Copyable {
    private String fieldOne;
    private String fieldTwo;

    @Copy.Ignore
    @Merge.Ignore
    private String ignoredField;

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

    public String getIgnoredField() {
        return ignoredField;
    }

    public void setIgnoredField(String ignoredField) {
        this.ignoredField = ignoredField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestBean mergeBean = (TestBean) o;
        return Objects.equals(fieldOne, mergeBean.fieldOne) &&
                Objects.equals(fieldTwo, mergeBean.fieldTwo) &&
                Objects.equals(ignoredField, mergeBean.ignoredField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldOne, fieldTwo, ignoredField);
    }
}
