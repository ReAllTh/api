package link.reallth.api.converter.factory;

import jakarta.annotation.Nonnull;
import link.reallth.api.constant.enums.Convertable;
import link.reallth.api.converter.IntegerToEnumConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Collections;
import java.util.Map;

/**
 * Integer -> Enum converter factory
 *
 * @author ReAllTh
 */
public class IntegerToEnumConverterFactory implements ConverterFactory<Integer, Convertable> {
    private static final Map<Class, Converter> CONVERTERS = Collections.emptyMap();

    @Override
    @Nonnull
    public <T extends Convertable> Converter<Integer, T> getConverter(@Nonnull Class<T> targetType) {
        Converter<Integer, T> converter = CONVERTERS.get(targetType);
        if (converter == null) {
            converter = new IntegerToEnumConverter<>(targetType);
            CONVERTERS.put(targetType, converter);
        }
        return converter;
    }

}
