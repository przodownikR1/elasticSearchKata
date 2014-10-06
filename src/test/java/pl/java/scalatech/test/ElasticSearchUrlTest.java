package pl.java.scalatech.test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
@Slf4j
public class ElasticSearchUrlTest {
    @Test
    public void shouldElasticSearchConnect() throws IOException {

        URL url = new URL("http://127.0.0.1:9200");
        URLConnection conn = url.openConnection();//
        log.info("+++  {}",CharStreams.toString(new InputStreamReader(conn.getInputStream(), Charsets.UTF_8)));
        
    }
}
