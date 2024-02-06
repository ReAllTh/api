package link.reallth.api.converter;

import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import link.reallth.api.constant.enums.CODES;
import link.reallth.api.constant.enums.ConvertableEnum;
import link.reallth.api.exception.BaseException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

import static link.reallth.api.constant.ValidateConst.INVALID_MSG_ROLE;

/**
 * String -> Enum converter for get request
 *
 * @author ReAllTh
 */
@Component
public class StringToEnumConverter implements Converter<String, ConvertableEnum> {
    private Map<String, ConvertableEnum> enumMap;

    @PostConstruct
    private void init() {
        enumMap = CollectionUtils.newHashMap(2);
        for (ConvertableEnum convertableEnum : ConvertableEnum.class.getEnumConstants())
            enumMap.put(convertableEnum.getName(), convertableEnum);
    }

    @Override
    public ConvertableEnum convert(@Nonnull String source) {
        ConvertableEnum role = enumMap.get(source);
        if (role == null)
            throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_ROLE);
        return role;
    }
}