package kz.edu.astanait.afisha.service;

import kz.edu.astanait.afisha.domain.AuthenticatedUser;
import kz.edu.astanait.afisha.domain.GeneratedToken;
import kz.edu.astanait.afisha.domain.LoginRequest;
import kz.edu.astanait.afisha.domain.UserResponse;

public interface UserAuthenticationService {

    GeneratedToken login(LoginRequest request);

    UserResponse getAuthenticatedUser(AuthenticatedUser user);
}