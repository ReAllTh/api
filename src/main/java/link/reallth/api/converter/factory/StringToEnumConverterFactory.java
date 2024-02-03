package link.reallth.api.converter.factory;

import jakarta.annotation.Nonnull;
import link.reallth.api.constant.enums.Convertable;
import link.reallth.api.converter.StringToEnumConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Collections;
import java.util.Map;

/**
 * String -> Enum converter factory
 *
 * @author ReAllTh
 */
public class StringToEnumConverterFactory implements ConverterFactory<String, Convertable> {
    private static final Map<Class, Converter> CONVERTERS = Collections.emptyMap();

    @Override
    @Nonnull
    public <T extends Convertable> Converter<String, T> getConverter(@Nonnull Class<T> targetType) {
        Converter<String, T> converter = CONVERTERS.get(targetType);
        if (converter == null) {
            converter = new StringToEnumConverter<>(targetType);
            CONVERTERS.put(targetType, converter);
        }
        return converter;
    }
}