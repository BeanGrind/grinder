package ops;

import grinder.ops.copy.Copyable;
import grinder.ops.merge.Mergeable;
import grinder.ops.validation.Validatable;

import java.util.Objects;

public class AltTestBean implements Copyable, Validatable, Mergeable {
    private String firstValue;
    private String secondValue;

    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AltTestBean that = (AltTestBean) o;
        return Objects.equals(firstValue, that.firstValue) &&
                Objects.equals(secondValue, that.secondValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue);
    }
}
