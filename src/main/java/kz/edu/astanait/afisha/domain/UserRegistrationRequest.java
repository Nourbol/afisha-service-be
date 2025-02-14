package kz.edu.astanait.afisha.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequest(@Size(min = 4, message = "Full name must be more than 4 characters")
                                      String fullName,
                                      @Email(message = "Incorrect Email")
                                      String email,
                                      @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
                                              message = "Password must be at least 8 characters long and include digits, uppercase and lowercase letters, and a special character")
                                      String password) {
}