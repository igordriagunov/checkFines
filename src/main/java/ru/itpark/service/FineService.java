package ru.itpark.service;

import org.springframework.stereotype.Service;
import ru.itpark.domain.Fine;
import ru.itpark.repository.FineRepository;

import java.util.List;

@Service
public class FineService {
    private final FineRepository fineRepository;

    public FineService(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    public List<Fine> findAllFines() {
        return fineRepository.findAllFines();
    }

    public List<Fine> findByCarNumber(String carNum, String regNum) {
        return fineRepository.findByCarNumber(carNum, regNum) ;
    }

}
