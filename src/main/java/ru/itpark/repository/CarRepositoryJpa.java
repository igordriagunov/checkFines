package ru.itpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.domain.Car;

import java.util.List;

public interface CarRepositoryJpa extends JpaRepository<Car, Integer> {
}
