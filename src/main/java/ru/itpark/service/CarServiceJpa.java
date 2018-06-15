package ru.itpark.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.domain.Car;

import java.util.List;

public interface CarServiceJpa extends JpaRepository<Car, Integer> {

    List<Car> findAllByCarNumber(String carNumber);

}
