package pl.java.scalatech.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:server.properties")
@Profile("dev")
@Slf4j
public class PropertiesLoader {
   
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        log.info("+++  propertySource -> prop profile launch");
        return new PropertySourcesPlaceholderConfigurer();
    }
    
   
}