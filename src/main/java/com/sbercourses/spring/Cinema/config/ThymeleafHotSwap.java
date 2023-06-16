package com.sbercourses.spring.Cinema.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeleafHotSwap {
    private final ThymeleafProperties thymeleafProperties;

    public ThymeleafHotSwap(ThymeleafProperties thymeleafProperties) {
        this.thymeleafProperties = thymeleafProperties;
    }

    @Value("${spring.thymeleaf.template_root}")
    private String templatesRoot;

    @Bean
    public ITemplateResolver defaultTemplateResolver()
    {
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setSuffix(thymeleafProperties.getSuffix());
        resolver.setPrefix(templatesRoot);
        resolver.setTemplateMode(thymeleafProperties.getMode());
        resolver.setCacheable(thymeleafProperties.isCache());
        return resolver;
    }
}
