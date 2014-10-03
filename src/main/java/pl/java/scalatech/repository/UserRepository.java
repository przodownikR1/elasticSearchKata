package pl.java.scalatech.repository;

import java.io.Serializable;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import pl.java.scalatech.domain.User;

/**
 * @author Sławomir Borowiec 
 * Module name : bootSetting
 * Creating time :  17 wrz 2014 09:49:29
 
 */
public interface UserRepository extends ElasticsearchRepository<User, Serializable>{
    
    User findUserByLogin(String login);

}
