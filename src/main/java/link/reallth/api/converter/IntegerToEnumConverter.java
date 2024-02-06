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
 * Integer -> Enum converter for post request
 *
 * @author ReAllTh
 */
@Component
public class IntegerToEnumConverter implements Converter<Integer, ConvertableEnum> {
    private Map<Integer, ConvertableEnum> enumMap;

    @PostConstruct
    private void init() {
        enumMap = CollectionUtils.newHashMap(2);
        for (ConvertableEnum convertableEnum : ConvertableEnum.class.getEnumConstants())
            enumMap.put(convertableEnum.getVal(), convertableEnum);
    }

    @Override
    public ConvertableEnum convert(@Nonnull Integer source) {
        ConvertableEnum role = enumMap.get(source);
        if (role == null)
            throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_ROLE);
        return role;
    }
}
