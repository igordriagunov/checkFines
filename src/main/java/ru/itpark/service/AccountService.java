package ru.itpark.service;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.itpark.repository.AccountRepository;


public interface AccountService
        extends UserDetailsService {

    boolean isAuthenticated();

    boolean hasRole(String authority);


}
