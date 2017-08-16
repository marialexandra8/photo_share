package com.maria.service.api;

import com.maria.model.authentication.AuthenticationRequest;
import com.maria.model.authentication.AuthenticationResponse;
import com.maria.model.token.Token;
import com.maria.security.PrincipalUser;

/**
 * Created   on 8/9/2017.
 */
public interface AuthenticationService {
    PrincipalUser authenticateByToken(String token);

    AuthenticationResponse authenticateAccount(AuthenticationRequest authenticationRequest);

}
