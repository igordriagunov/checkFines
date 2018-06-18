package ru.itpark.service;

import org.springframework.stereotype.Service;
import ru.itpark.domain.Car;
import ru.itpark.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Optional<Car> findById(int id) {
        return carRepository.findById(id);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> findAllByAccountId(int accountId) {
        return carRepository.findAllByAccount_Id(accountId);
    }

    public List<Car> findAllByCarNumber(String carNumber) {
        return carRepository.findAllByCarNumber(carNumber);
    }

    public void save(Car car) {
        carRepository.save(car);
    }

    public void deleteById(int id) {
        carRepository.deleteById(id);
    }
}
