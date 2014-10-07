package pl.java.scalatech.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Service;

import pl.java.scalatech.config.elastic.ElasticSearchConfig;
import pl.java.scalatech.config.elastic.ElasticSearchRemoteConfig;
@Configurable
@ComponentScan(basePackages="pl.java.scalatech.service",useDefaultFilters=false,includeFilters={@Filter(Service.class)})
@PropertySource(value={"server.properties","elasticSearch.properties"},ignoreResourceNotFound=false)
@Import(value={PropertiesLoader.class,ElasticSearchConfig.class,ElasticSearchRemoteConfig.class})
public class ServiceConfig {

}
