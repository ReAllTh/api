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
 * String => Enum converter factory
 *
 * @author ReAllTh
 */
@Component
public class StringToEnumConverterFactory implements ConverterFactory<String, ConvertableEnum> {
    @NotNull
    @Override
    public <T extends ConvertableEnum> Converter<String, T> getConverter(@NotNull Class<T> targetType) {
        return new StringToEnumConverter<>(targetType);
    }

    private static class StringToEnumConverter<T extends ConvertableEnum> implements Converter<String, T> {
        private final Map<String, T> enumMap;

        StringToEnumConverter(Class<T> targetType) {
            enumMap = CollectionUtils.newHashMap(4);
            for (T convertableEnum : targetType.getEnumConstants())
                enumMap.put(convertableEnum.getName(), convertableEnum);
        }

        @Override
        public T convert(@Nonnull String source) {
            T role = enumMap.get(source);
            if (role == null)
                throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_ROLE);
            return role;
        }
    }
}
