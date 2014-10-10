package pl.java.scalatech.service;

import pl.java.scalatech.domain.User;
import pl.java.scalatech.service.common.PaginationService;

/**
 * @author Sławomir Borowiec
 *         Module name : bootSetting
 *         Creating time : 17 wrz 2014 09:49:20
 */
public interface UserService extends PaginationService<User,Long> {
    
    User findByLogin(String login);

}
