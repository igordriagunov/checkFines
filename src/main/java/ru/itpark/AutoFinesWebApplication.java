package ru.itpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itpark.domain.Account;
import ru.itpark.domain.Fine;
import ru.itpark.repository.AccountRepository;
import ru.itpark.repository.FineRepositoryJpa;
import ru.itpark.service.MailSenderService;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class AutoFinesWebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(AutoFinesWebApplication.class, args);
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        AccountRepository repository = context.getBean(AccountRepository.class);
        FineRepositoryJpa fineRepository = context.getBean(FineRepositoryJpa.class);
        MailSenderService mailService = context.getBean(MailSenderService.class);

        repository.saveAll(
                List.of(
                        new Account(
                                0,
                                "admin",
                                "e@mail.ru",
                                encoder.encode("admin"),
                                List.of(new SimpleGrantedAuthority("ROLE_ADMIN")),
                                true,
                                true,
                                true,
                                true
                        ),
                        new Account(
                                0,
                                "user",
                                "e@mail.ru",
                                encoder.encode("user"),
                                List.of(new SimpleGrantedAuthority("ROLE_USER")),
                                true,
                                true,
                                true,
                                true
                        )
                )
        );

        fineRepository.saveAll(
                List.of(
                        new Fine(
                                0,
                                "о777оо77",
                                "1616111222",
                                "09.05.18",
                                "КоАП статья и тд",
                                1500
                        ),


                        new Fine(
                                0,
                                "о777оо77",
                                "1616111222",
                                "09.05.18",
                                "КоАП статья и тд",
                                500
                        ),
                        new Fine(
                                0,
                                "о777оо77",
                                "1616111222",
                                "09.05.18",
                                "КоАП статья и тд",
                                2500
                        )
                )
        );

        mailService.send(
                "talummoney@yandex.ru",
                "hello",
                "It's test msg");
    }
}
