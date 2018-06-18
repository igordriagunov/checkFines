package ru.itpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.domain.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findAllByCarNumber(String carNumber);

    @Override
    List<Car> findAllById(Iterable<Integer> id);

    List<Car> findAllByAccount_Id(int accountId);

    void removeById(int id);
}
