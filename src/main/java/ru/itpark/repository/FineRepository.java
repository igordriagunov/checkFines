package ru.itpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itpark.domain.Fine;

import java.util.List;
import java.util.Map;

@Repository
public interface FineRepository
        extends JpaRepository< Fine,Integer > {
    List<Fine> findAllByCarNumAndAndRegNum(String carNum, String regNum);
}
