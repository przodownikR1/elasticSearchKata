package pl.java.scalatech.config;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.HateoasSortHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.PagedResourcesAssemblerArgumentResolver;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author Sławomir Borowiec
 *         Module name : bootSetting
 *         Creating time : 17 wrz 2014 09:50:04
 */
@Configuration
@ComponentScan(basePackages = { "pl.java.scalatech.controller", "pl.java.scalatech.rest" }, useDefaultFilters = false, includeFilters = {
        @Filter(RestController.class), @Filter(Component.class), @Filter(ControllerAdvice.class) })
@Slf4j
public class RestConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(pageableResolver());
        argumentResolvers.add(sortResolver());
        argumentResolvers.add(pagedResourcesAssemblerArgumentResolver());
    }

    @Bean
    public DomainClassConverter<FormattingConversionService> domainClassConverter() {
        return new DomainClassConverter<>(mvcConversionService());
    }

    @Bean
    public HateoasPageableHandlerMethodArgumentResolver pageableResolver() {
        return new HateoasPageableHandlerMethodArgumentResolver(sortResolver());
    }

    @Bean
    public HateoasSortHandlerMethodArgumentResolver sortResolver() {
        return new HateoasSortHandlerMethodArgumentResolver();
    }

    @Bean
    public PagedResourcesAssembler<?> pagedResourcesAssembler() {
        return new PagedResourcesAssembler<>(pageableResolver(), null);
    }

    @Bean
    public PagedResourcesAssemblerArgumentResolver pagedResourcesAssemblerArgumentResolver() {
        return new PagedResourcesAssemblerArgumentResolver(pageableResolver(), null);
    }

    public @Bean RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Override
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping hm = super.requestMappingHandlerMapping();
        hm.setRemoveSemicolonContent(false);
        return hm;
    }

   /* @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }*/
}
