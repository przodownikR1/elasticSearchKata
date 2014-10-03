package pl.java.scalatech.config;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

// import org.elasticsearch.client.Client;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import pl.java.scalatech.domain.User;

@Configuration
@EnableElasticsearchRepositories("pl.java.scalatech.repository")
public class ElasticSearchConfiguration {
    @Bean
    public Client client() {
        return nodeBuilder().clusterName("przodownik").local(true).node().client();
    }

    /*
     * @Bean
     * public ElasticsearchTemplate elasticsearchTemplate() {
     * return new ElasticsearchTemplate(client());
     * }
     */

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() {
        ImmutableSettings.Builder settings = ImmutableSettings.settingsBuilder().put("http.enabled", "true");
        ElasticsearchTemplate template = new ElasticsearchTemplate(NodeBuilder.nodeBuilder().settings(settings).local(true).clusterName("przodownik").node()
                .client());

        if (!template.indexExists(User.class)) {
            template.createIndex(User.class);
            template.putMapping(User.class);
        }

        return template;
    }
    

}