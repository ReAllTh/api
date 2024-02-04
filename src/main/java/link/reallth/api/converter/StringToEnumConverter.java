package link.reallth.api.converter;

import jakarta.annotation.Nonnull;
import link.reallth.api.constant.enums.CODES;
import link.reallth.api.constant.enums.Convertable;
import link.reallth.api.exception.BaseException;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;
import java.util.Map;

/**
 * String -> Enum converter for get request
 *
 * @author ReAllTh
 */
public class StringToEnumConverter<T extends Convertable> implements Converter<String, T> {
    private final Map<String, T> enumMap;
    private final String targetTypeNam;

    public StringToEnumConverter(Class<T> targetType) {
        this.targetTypeNam = targetType.getTypeName();
        this.enumMap = Collections.emptyMap();
        for (T e : targetType.getEnumConstants())
            enumMap.put(e.getVal().toString(), e);
    }

    /**
     * String -> Enum
     *
     * @param source val to be converted
     * @return target enum
     */
    @Override
    public T convert(@Nonnull String source) {
        if (!enumMap.containsKey(source))
            throw new BaseException(CODES.ERROR_PARAM, "invalid " + targetTypeNam);
        return enumMap.get(source);
    }
}