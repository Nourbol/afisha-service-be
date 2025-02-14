package kz.edu.astanait.afisha.service;


import kz.edu.astanait.afisha.domain.UserRegistrationRequest;
import kz.edu.astanait.afisha.domain.UserResponse;

public interface UserFactory {

    UserResponse create(UserRegistrationRequest request);
}