package pl.java.scalatech.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import pl.java.scalatech.domain.Config;

public interface ConfigRepository extends ElasticsearchRepository<Config, String> {

}
