package grinder.custom.converter.converters;

import grinder.custom.converter.Converter;

import java.util.Date;

public class LongToDateConverter implements Converter<Date, Long> {

    @Override
    public Date convert(Long aLong) {
        return new Date(aLong);
    }

    @Override
    public Long reverse(Date date) {
        return date.getTime();
    }
}
