package link.reallth.api.config;

import link.reallth.api.converter.factory.IntegerToEnumConverterFactory;
import link.reallth.api.converter.factory.StringToEnumConverterFactory;
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
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new IntegerToEnumConverterFactory());
        registry.addConverterFactory(new StringToEnumConverterFactory());
    }
}
