package com.maria.service.user;

import com.maria.model.account.Gender;
import com.maria.model.user.CreateUserRequest;
import com.maria.model.user.User;
import com.maria.repository.UserRepository;
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

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        LocalDate birthday = createUserRequest.getBirthday();
        String name = createUserRequest.getName();
        String email = createUserRequest.getEmail();
        Gender gender = createUserRequest.getGender();

        return userRepository.createUser(name, email, birthday, gender);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }
}
