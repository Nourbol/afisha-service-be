package kz.edu.astanait.afisha.service.impl;

import kz.edu.astanait.afisha.domain.AuthenticatedUser;
import kz.edu.astanait.afisha.entity.TokenEntity;
import kz.edu.astanait.afisha.mapper.UserMapper;
import kz.edu.astanait.afisha.repository.TokenRepository;
import kz.edu.astanait.afisha.repository.UserRepository;
import kz.edu.astanait.afisha.service.UserReader;
import kz.edu.astanait.afisha.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {

    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final UserMapper userMapper;

    @Override
    public AuthenticatedUser getByEmail(String email) {
        return repository.findByEmail(email)
            .map(userMapper::mapToAuthenticatedUser)
            .orElseThrow(() -> new UsernameNotFoundException("User with email %s was not found".formatted(email)));
    }

    @Override
    public Optional<AuthenticatedUser> getByToken(final String token) {
        var hashedToken = TokenUtils.hash(token);
        return tokenRepository.findByHashAndExpiredAtAfter(hashedToken, LocalDateTime.now())
                .map(TokenEntity::getUser)
                .map(userMapper::mapToAuthenticatedUser);
    }
}