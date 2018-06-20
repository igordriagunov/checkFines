package ru.itpark.service;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itpark.domain.Account;
import ru.itpark.domain.Car;
import ru.itpark.exception.UsernameAlreadyExistsException;
import ru.itpark.repository.AccountRepository;
import ru.itpark.repository.CarRepository;

import java.util.List;

@Primary
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    private final PasswordEncoder encoder;
    private final AccountRepository accountRepository;
    private final MailSenderService mailSenderService;
    private final CarRepository carRepository;

    public AccountServiceImpl(PasswordEncoder encoder, AccountRepository accountRepository,
                              MailSenderService mailSenderService, CarRepository carRepository) {
        this.encoder = encoder;
        this.accountRepository = accountRepository;
        this.mailSenderService = mailSenderService;
        this.carRepository = carRepository;
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
                        e -> e.getAuthority().equals("ROLE_" + role)
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
            throw new UsernameAlreadyExistsException("username already exists");
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

        if (!org.springframework.util.StringUtils.isEmpty(account.getEMail())) {

            String message = String.format(
                    "Hello, %s , welcome to test site ",
                    account.getUsername()

            );

            mailSenderService.send(account.getEMail(), "Localhost:8080", message);
        }

    }

    public void addMyCar(Car car) {

        Car saved = carRepository.save(car);
    }
}
