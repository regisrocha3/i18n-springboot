package rrlabs.i18n.config.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
public class I18nLocale extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {
    final List<Locale> LOCALES = Arrays.asList(new Locale("en-US"), new Locale("de-DE"), new Locale("pt-BR"));

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        final String headerLang = request.getHeader("Accept-Language");
        return headerLang == null || headerLang.isEmpty()
                ? Locale.getDefault()
                : Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        final ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
        bundle.setBasename("message");
        bundle.setDefaultEncoding("UTF-8");
        bundle.setUseCodeAsDefaultMessage(true);
        return bundle;
    }

}
