package ops.map;

import grinder.custom.converter.Converter;

public class AbsoluteValueConverter implements Converter<Long, Long> {

    @Override
    public Long convert(Long aLong) {
        if (aLong == null) {
            return null;
        }
        
        return aLong < 0 ? aLong * - 1 : aLong;
    }

    @Override
    public Long reverse(Long aLong) {
        return null;
    }
}
