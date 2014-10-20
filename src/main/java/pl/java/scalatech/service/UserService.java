package pl.java.scalatech.service;

import pl.java.scalatech.domain.User;
import pl.java.scalatech.service.common.PaginationService;


public interface UserService extends PaginationService<User,Long> {
    
    User findByLogin(String login);

    void deleteAll();
}
