package kz.edu.astanait.afisha.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.userdetails.User;
import java.util.Collections;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class AuthenticatedUser extends User {

    private final UUID id;
    private final String fullName;
    private final String email;

    public AuthenticatedUser(final UUID id,
                             final String fullName,
                             final String email,
                             final String password) {
        super(email, password, Collections.emptyList());
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }
}