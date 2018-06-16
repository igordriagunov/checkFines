package ru.itpark.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
    @Scheduled(fixedRate = 86_400_000)
    public void cron() {
        System.out.println("executed");
        // TODO: send email


    }
}
