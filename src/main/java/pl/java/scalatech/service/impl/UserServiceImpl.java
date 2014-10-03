package pl.java.scalatech.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.java.scalatech.domain.User;
import pl.java.scalatech.repository.UserRepository;
import pl.java.scalatech.service.UserService;
import pl.java.scalatech.service.common.impl.PaginationAbstactService;

/**
 * @author Sławomir Borowiec
 *         Module name : bootSetting
 *         Creating time : 17 wrz 2014 09:49:17
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends PaginationAbstactService<User, Long> implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

}
