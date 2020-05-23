package ops.validation;

import grinder.ops.Validator;

public class PositiveNumberValidator implements Validator<Long> {

    @Override
    public boolean isValid(Long number) {
        return number > 0L;
    }
}
