package pl.java.scalatech.config.elastic;

import org.elasticsearch.client.Client;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import pl.java.scalatech.domain.User;

@Configuration
@EnableConfigurationProperties(ElasticsearchProperties.class)
@EnableElasticsearchRepositories("pl.java.scalatech.repository")
public abstract class ElasticCommon {

    
    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() { 
        ElasticsearchTemplate template = new ElasticsearchTemplate(client());

        if (!template.indexExists(User.class)) {
            template.createIndex(User.class);
            template.putMapping(User.class);
        }

        return template;
    }
    
    abstract Client client();
    
}
