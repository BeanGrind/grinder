package ops;

import grinder.annotation.Copy;
import grinder.annotation.Mapping;
import grinder.annotation.Merge;
import grinder.annotation.Validate;
import grinder.ops.copy.Copyable;
import grinder.ops.map.Mappable;
import grinder.ops.merge.Mergeable;
import grinder.ops.validation.Validatable;

import java.util.Objects;

public class TestBean implements Mergeable, Copyable, Mappable, Validatable {
    private String fieldOne;
    private String fieldTwo;

    @Validate(pattern = "[0-9]{2}-[0-9]{3}")
    private String patternField;

    @Copy.Ignore
    @Merge.Ignore
    @Mapping.Ignore
    @Validate.Ignore
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

    public String getPatternField() {
        return patternField;
    }

    public void setPatternField(String patternField) {
        this.patternField = patternField;
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
        TestBean testBean = (TestBean) o;
        return Objects.equals(fieldOne, testBean.fieldOne) &&
                Objects.equals(fieldTwo, testBean.fieldTwo) &&
                Objects.equals(patternField, testBean.patternField) &&
                Objects.equals(ignoredField, testBean.ignoredField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldOne, fieldTwo, patternField, ignoredField);
    }
}
