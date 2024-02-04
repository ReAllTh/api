package link.reallth.api.config;

import jakarta.annotation.Resource;
import link.reallth.api.converter.IntegerToROLESConverter;
import link.reallth.api.converter.StringToROLESConverter;
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
    private IntegerToROLESConverter integerToROLESConverter;
    @Resource
    private StringToROLESConverter stringToROLESConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(integerToROLESConverter);
        registry.addConverter(stringToROLESConverter);
    }
}
