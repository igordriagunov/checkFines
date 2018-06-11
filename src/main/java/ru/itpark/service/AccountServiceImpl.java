package ru.itpark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itpark.domain.Account;
import ru.itpark.exception.UsernameAlreadyExistsException;
import ru.itpark.repository.AccountRepository;

import java.util.List;

@Primary
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    private final PasswordEncoder encoder;
    private final AccountRepository accountRepository;

    public AccountServiceImpl(PasswordEncoder encoder, AccountRepository accountRepository) {
        this.encoder = encoder;
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean isAuthenticated() {
        return !hasRole("ANONYMOUS");
    }

    @Override
    public boolean hasRole(String role) {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(
                        e -> e.getAuthority().equals("ROLE" + role)
                );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                username + "not found or invalid password"
                        )
                );
    }

    public void createAccount(String username, String password, String eMail) {
        if (accountRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException();
        }

        Account account = new Account(
                0,
                username,
                eMail,
                encoder.encode(password),
                List.of(new SimpleGrantedAuthority("ROLE_USER")),
                true,
                true,
                true,
                true
        );
        accountRepository.save(account);

    }
}
