package link.reallth.api.converter;

import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import link.reallth.api.constant.enums.CODES;
import link.reallth.api.constant.enums.ROLES;
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
public class IntegerToROLESConverter implements Converter<Integer, ROLES> {
    private Map<Integer, ROLES> enumMap;

    @PostConstruct
    private void init() {
        enumMap = CollectionUtils.newHashMap(2);
        for (ROLES role : ROLES.class.getEnumConstants())
            enumMap.put(role.getVal(), role);
    }

    @Override
    public ROLES convert(@Nonnull Integer source) {
        ROLES role = enumMap.get(source);
        if (role == null)
            throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_ROLE);
        return role;
    }
}
