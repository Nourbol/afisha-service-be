package kz.edu.astanait.afisha.service;

import kz.edu.astanait.afisha.domain.AuthenticatedUser;
import java.util.Optional;

public interface UserReader {

    AuthenticatedUser getByEmail(String token);

    Optional<AuthenticatedUser> getByToken(String token);
}