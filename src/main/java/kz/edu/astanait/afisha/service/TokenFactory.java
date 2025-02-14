package kz.edu.astanait.afisha.service;

import kz.edu.astanait.afisha.domain.GeneratedToken;

public interface TokenFactory {

    GeneratedToken create(String email);
}