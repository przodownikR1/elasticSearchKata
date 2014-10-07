package pl.java.scalatech.config.elastic;

import static org.elasticsearch.common.collect.Lists.newArrayList;

import java.util.List;

import javax.annotation.PostConstruct;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.base.Splitter;

@Configuration
@Profile("remote")
public class ElasticSearchRemoteConfig extends ElasticCommon {
    @Value("${es}")
    private String es;

    private List<String> nodes = newArrayList();

    @PostConstruct
    public void init() {
        nodes = Splitter.on(",").splitToList(es);
    }

    private InetSocketTransportAddress toAddress(String address) {
        int port = 9300;
        List<String> slitted = Splitter.on(":").splitToList(address);
        if (slitted.size() > 1) {
            port = Integer.parseInt(slitted.get(1));
        }
        return new InetSocketTransportAddress(slitted.get(0), port);
    }

    @Override
    @Bean
    public Client client() {
        ImmutableSettings.Builder builder = ImmutableSettings.settingsBuilder();
        TransportClient client = new TransportClient(builder.build());
        nodes.forEach(s -> client.addTransportAddresses(toAddress(s)));
        return client;

    }

}
