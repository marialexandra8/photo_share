package com.maria.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created  on 8/9/2017.
 */
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
    //useful for third party authentication
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TokenBasedAuthentication.class.isAssignableFrom(aClass);
    }
}
