package pl.java.scalatech.config.elastic;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

// import org.elasticsearch.client.Client;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class ElasticSearchConfig extends ElasticCommon {
    @Override
    @Bean
    public Client client() {
        ImmutableSettings.Builder settings = ImmutableSettings.settingsBuilder().put("http.enabled", "true");
        return nodeBuilder().settings(settings).clusterName("przodownik").local(true).node().client();
    }

   

}