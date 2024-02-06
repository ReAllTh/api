package link.reallth.api.config;

import jakarta.annotation.Resource;
import link.reallth.api.converter.IntegerToEnumConverter;
import link.reallth.api.converter.StringToDateConverter;
import link.reallth.api.converter.StringToEnumConverter;
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
    private IntegerToEnumConverter integerToEnumConverter;
    @Resource
    private StringToEnumConverter stringToEnumConverter;
    @Resource
    private StringToDateConverter stringToDateConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(integerToEnumConverter);
        registry.addConverter(stringToEnumConverter);
        registry.addConverter(stringToDateConverter);
    }
}
