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
import ru.itpark.repository.CarRepository;
import ru.itpark.repository.FineRepository;
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
        FineRepository fineRepository = context.getBean(FineRepository.class);
        MailSenderService mailService = context.getBean(MailSenderService.class);
        CarRepository carRepository = context.getBean(CarRepository.class);

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
                                "09.04.18",
                                "КоАП : статья нарушения",
                                1500
                        ),


                        new Fine(
                                0,
                                "о777оо77",
                                "1616111222",
                                "15.05.18",
                                "КоАП : статья нарушения",
                                500
                        ),
                        new Fine(
                                0,
                                "о777оо77",
                                "1616111222",
                                "19.05.18",
                                "КоАП : статья нарушения",
                                2500
                        ),
                        new Fine(
                                0,
                                "т116тт116",
                                "1818333555",
                                "22.05.18",
                                "КоАП : статья нарушения",
                                2000
                        )
                )
        );

//        carRepository.saveAll(
//                List.of(
//                        new Car(0,
//                                "т116тт116",
//                                "1818333555"
//                        )
//                )
//        );

//        mailService.send(
//                "talummoney@yandex.ru",
//                "hello",
//                "It's test msg");
//
//
    }
}
