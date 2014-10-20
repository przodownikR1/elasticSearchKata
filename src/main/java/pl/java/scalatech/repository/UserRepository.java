package pl.java.scalatech.repository;

import java.io.Serializable;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import pl.java.scalatech.domain.User;


public interface UserRepository extends ElasticsearchRepository<User, Serializable>{
    
    User findUserByLogin(String login);

}
