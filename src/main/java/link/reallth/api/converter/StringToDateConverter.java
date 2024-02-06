package link.reallth.api.converter;

import jakarta.annotation.Nonnull;
import link.reallth.api.constant.enums.CODES;
import link.reallth.api.exception.BaseException;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

/**
 * String -> Date converter
 *
 * @author ReAllTh
 */
@Component
public class StringToDateConverter implements Converter<String, Date> {

    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Date convert(@Nonnull String source) {
        try {
            return DateUtils.parseDate(source, DATE_PATTERN);
        } catch (ParseException e) {
            throw new BaseException(CODES.ERROR_PARAM, e.getMessage());
        }
    }
}
