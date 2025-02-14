package kz.edu.astanait.afisha.service.impl;

import kz.edu.astanait.afisha.domain.UserRegistrationRequest;
import kz.edu.astanait.afisha.domain.UserResponse;
import kz.edu.astanait.afisha.entity.UserEntity;
import kz.edu.astanait.afisha.exception.AlreadyExistsException;
import kz.edu.astanait.afisha.mapper.UserMapper;
import kz.edu.astanait.afisha.repository.UserRepository;
import kz.edu.astanait.afisha.service.UserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFactoryImpl implements UserFactory {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse create(final UserRegistrationRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.email())) {
            throw new AlreadyExistsException("Email is already taken by another user");
        }
        final String encodedPassword = passwordEncoder.encode(request.password());
        final UserEntity user = userRepository.save(userMapper.mapToUserEntity(request, encodedPassword));
        return userMapper.mapToUserResponse(user);
    }
}