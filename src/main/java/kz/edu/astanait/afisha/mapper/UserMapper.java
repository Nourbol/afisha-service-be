package kz.edu.astanait.afisha.mapper;

import kz.edu.astanait.afisha.domain.AuthenticatedUser;
import kz.edu.astanait.afisha.domain.UserRegistrationRequest;
import kz.edu.astanait.afisha.domain.UserResponse;
import kz.edu.astanait.afisha.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity mapToUserEntity(final UserRegistrationRequest registrationRequest,
                                      final String hashedPassword) {
        final UserEntity user = new UserEntity();
        user.setName(registrationRequest.fullName());
        user.setEmail(registrationRequest.email());
        user.setPassword(hashedPassword);
        return user;
    }

    public UserResponse mapToUserResponse(final UserEntity userEntity) {
        return new UserResponse(userEntity.getId(), userEntity.getName(), userEntity.getEmail());
    }

    public UserResponse mapToUserResponse(final AuthenticatedUser authenticatedUser) {
        return new UserResponse(authenticatedUser.getId(), authenticatedUser.getFullName(), authenticatedUser.getEmail());
    }

    public AuthenticatedUser mapToAuthenticatedUser(final UserEntity userEntity) {
        return new AuthenticatedUser(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getPassword());
    }
}