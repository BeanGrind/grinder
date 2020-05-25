package ops;

import grinder.ops.copy.Copyable;

import java.util.Objects;

public class AltTestBean implements Copyable {
    private String firstValue;

    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AltTestBean that = (AltTestBean) o;
        return Objects.equals(firstValue, that.firstValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue);
    }
}
