package com.maria.service.api;

import com.maria.model.user.CreateUserRequest;
import com.maria.model.user.User;

/**
 * Created on 8/10/2017.
 */
public interface UserService {
    User createUser(CreateUserRequest createUserRequest);

    User findById(int id);
}
