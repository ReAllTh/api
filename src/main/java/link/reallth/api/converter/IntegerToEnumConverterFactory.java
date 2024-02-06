package link.reallth.api.converter;

import jakarta.annotation.Nonnull;
import link.reallth.api.constant.enums.CODES;
import link.reallth.api.constant.enums.ConvertableEnum;
import link.reallth.api.exception.BaseException;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

import static link.reallth.api.constant.ValidateConst.INVALID_MSG_ROLE;

/**
 * Integer => Enum converter factory
 *
 * @author ReAllTh
 */
@Component
public class IntegerToEnumConverterFactory implements ConverterFactory<Integer, ConvertableEnum> {

    @NotNull
    @Override
    public <T extends ConvertableEnum> Converter<Integer, T> getConverter(@NotNull Class<T> targetType) {
        return new IntegerToEnumConverter<>(targetType);
    }

    private static class IntegerToEnumConverter<T extends ConvertableEnum> implements Converter<Integer, T> {
        private final Map<Integer, T> enumMap;

        IntegerToEnumConverter(Class<T> targetType) {
            enumMap = CollectionUtils.newHashMap(4);
            for (T convertableEnum : targetType.getEnumConstants())
                enumMap.put(convertableEnum.getVal(), convertableEnum);
        }

        @Override
        public T convert(@Nonnull Integer source) {
            T role = enumMap.get(source);
            if (role == null)
                throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_ROLE);
            return role;
        }
    }
}
