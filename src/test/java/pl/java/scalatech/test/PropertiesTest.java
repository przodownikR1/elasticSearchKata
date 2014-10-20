package pl.java.scalatech.test;

import static org.fest.assertions.Assertions.assertThat;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.java.scalatech.config.ServiceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ServiceConfig.class })
@ActiveProfiles(value = "local")
@Slf4j
public class PropertiesTest {
    @Value("${es}")
    private String es;

    @Value("${server.port}")
    private String app;

    @Autowired
    private Environment env;

    @Test
    public void shouldPropertiesRead() {
        log.info("!!! port : {}", app);
        assertThat(es).isEqualTo("localhost:9300");
    }

    @Test
    public void shouldPropertiesEnvRead() {
        assertThat(env.getProperty("es")).isEqualTo("localhost:9300");
        assertThat(es).isEqualTo("localhost:9300");
    }
}
