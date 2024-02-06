package link.reallth.api.config;

import jakarta.annotation.Resource;
import link.reallth.api.converter.IntegerToEnumConverterFactory;
import link.reallth.api.converter.StringToDateConverter;
import link.reallth.api.converter.StringToEnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * converter factory
 *
 * @author ReAllTh
 */
@Configuration
public class ConverterConfig implements WebMvcConfigurer {
    @Resource
    private IntegerToEnumConverterFactory integerToEnumConverterFactory;
    @Resource
    private StringToEnumConverterFactory stringToEnumConverterFactory;
    @Resource
    private StringToDateConverter stringToDateConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(integerToEnumConverterFactory);
        registry.addConverterFactory(stringToEnumConverterFactory);
        registry.addConverter(stringToDateConverter);
    }
}
