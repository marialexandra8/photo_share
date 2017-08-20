package com.maria.service.user;

import com.maria.model.account.Gender;
import com.maria.model.user.CreateUserRequest;
import com.maria.model.user.User;
import com.maria.repository.UserRepository;
import com.maria.service.api.FileService;
import com.maria.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Created on 8/10/2017.
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private FileService fileService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, FileService fileService) {
        this.userRepository = userRepository;
        this.fileService = fileService;
    }

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        LocalDate birthday = createUserRequest.getBirthday();
        String name = createUserRequest.getName();
        String email = createUserRequest.getEmail();
        Gender gender = createUserRequest.getGender();
        int accountId = createUserRequest.getAccountId();

        User user = userRepository.createUser(name, email, birthday, gender, accountId);
        return addLogoUrl(user);
    }

    @Override
    public User findById(int id) {
        User user = userRepository.findById(id);
        return addLogoUrl(user);
    }

    @Override
    public User findByAccountId(int accountId) {
        User user = userRepository.findByAccountId(accountId);
        return addLogoUrl(user);
    }

    private User addLogoUrl(User user) {
        return user.setLogoPath(fileService.getRelativePathForUserLogo(user.getLogoName()));
    }


}
