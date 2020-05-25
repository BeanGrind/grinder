package ops;

import grinder.annotation.Copy;
import grinder.annotation.Mapping;
import grinder.annotation.Merge;
import grinder.annotation.Validate;
import grinder.custom.converter.ConverterDirection;
import grinder.ops.copy.Copyable;
import grinder.ops.map.Mappable;
import grinder.ops.merge.Mergeable;
import grinder.ops.validation.Validatable;
import ops.map.AbsoluteValueConverter;
import ops.validation.PositiveNumberValidator;

import java.util.Objects;

public class TestBean implements Mergeable, Copyable, Mappable, Validatable {
    private String fieldOne;
    private String fieldTwo;

    @Validate(pattern = "[0-9]{2}-[0-9]{3}", allowNull = true)
    private String patternField;

    @Mapping(converter = AbsoluteValueConverter.class)
    @Validate(validator = PositiveNumberValidator.class)
    private Long positiveNumber;

    @Mapping(converter = AbsoluteValueConverter.class, convertDirection = ConverterDirection.TO_MAP)
    private Long negativeNumber;

    @Copy
    @Merge
    @Validate
    private AltTestBean deepCopyBean;

    @Copy(deepCopy = false)
    @Merge(deepMerge = false)
    @Validate(deepValidate = false)
    private AltTestBean basicCopyBean;

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

    public Long getPositiveNumber() {
        return positiveNumber;
    }

    public void setPositiveNumber(Long positiveNumber) {
        this.positiveNumber = positiveNumber;
    }

    public Long getNegativeNumber() {
        return negativeNumber;
    }

    public void setNegativeNumber(Long negativeNumber) {
        this.negativeNumber = negativeNumber;
    }

    public AltTestBean getDeepCopyBean() {
        return deepCopyBean;
    }

    public void setDeepCopyBean(AltTestBean deepCopyBean) {
        this.deepCopyBean = deepCopyBean;
    }

    public AltTestBean getBasicCopyBean() {
        return basicCopyBean;
    }

    public void setBasicCopyBean(AltTestBean basicCopyBean) {
        this.basicCopyBean = basicCopyBean;
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
                Objects.equals(positiveNumber, testBean.positiveNumber) &&
                Objects.equals(negativeNumber, testBean.negativeNumber) &&
                Objects.equals(deepCopyBean, testBean.deepCopyBean) &&
                Objects.equals(basicCopyBean, testBean.basicCopyBean) &&
                Objects.equals(ignoredField, testBean.ignoredField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldOne, fieldTwo, patternField, positiveNumber, negativeNumber, deepCopyBean, basicCopyBean, ignoredField);
    }
}
