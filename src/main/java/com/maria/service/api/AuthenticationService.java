package com.maria.service.api;

import com.maria.security.PrincipalUser;

/**
 * Created   on 8/9/2017.
 */
public interface AuthenticationService {
    PrincipalUser authenticateByToken(String token);
}
