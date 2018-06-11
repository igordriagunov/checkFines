package ru.itpark.service;

import org.springframework.stereotype.Service;
import ru.itpark.domain.Fine;
import ru.itpark.repository.FineRepositoryJpa;

import java.util.List;

@Service
public class FineService {
    private final FineRepositoryJpa fineRepository;

    public FineService(FineRepositoryJpa fineRepository) {
        this.fineRepository = fineRepository;
    }


//    public List<Fine> findAllFines() {
//        return fineRepository.findAllFines();
//    }
//
//    public List<Fine> findByCarNumber(String carNum, String regNum) {
//        return fineRepository.findByCarNumber(carNum, regNum) ;
//    }

    public List<Fine> findFineByCarNumberRegNumber(String carNum, String regNum) {
        return fineRepository.findAllByCarNumAndAndRegNum(carNum, regNum);
    }

}
