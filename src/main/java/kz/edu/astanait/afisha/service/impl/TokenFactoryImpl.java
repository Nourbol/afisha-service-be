package kz.edu.astanait.afisha.service.impl;

import kz.edu.astanait.afisha.domain.GeneratedToken;
import kz.edu.astanait.afisha.entity.TokenEntity;
import kz.edu.astanait.afisha.exception.RecordNotFoundException;
import kz.edu.astanait.afisha.repository.TokenRepository;
import kz.edu.astanait.afisha.repository.UserRepository;
import kz.edu.astanait.afisha.service.TokenFactory;
import kz.edu.astanait.afisha.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class TokenFactoryImpl implements TokenFactory {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final Duration expireAfter;

    public TokenFactoryImpl(final UserRepository userRepository,
                            final TokenRepository tokenRepository,
                            final @Value("${spring.security.token.expire-after}") Duration expireAfter) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.expireAfter = expireAfter;
    }

    @Override
    public GeneratedToken create(final String email) {
        var plainText = TokenUtils.createPlainTextToken();
        var hashedToken = TokenUtils.hash(plainText);
        var user = userRepository.findByEmail(email)
                                 .orElseThrow(() -> RecordNotFoundException.userNotFoundByEmail(email));
        var expiredAt = LocalDateTime.now().plus(expireAfter);
        tokenRepository.save(new TokenEntity(hashedToken, expiredAt, user));
        return new GeneratedToken(new String(plainText, StandardCharsets.UTF_8), expiredAt);
    }
}