package ru.itpark.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itpark.domain.Fine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class FineRepositoryImpl {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FineRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Fine> findAllFines() {
        return jdbcTemplate.query(
                "SELECT id, carNum, regNum, dateOfFine, info, price FROM fines",
                (resultSet, i) -> new Fine(
                        resultSet.getInt("id"),
                        resultSet.getString("carNum"),
                        resultSet.getString("regNum"),
                        resultSet.getString("dateOfFine"),
                        resultSet.getString("info"),
                        resultSet.getInt("price")
                )
        );
    }

    public List<Fine> findByCarNumber(String carNum, String regNum) {
        return jdbcTemplate.query(
                "SELECT id, carNum, regNum, dateOfFine, info, price FROM fines WHERE " +
                        "carNum = :carNum AND regNum = :regNum ",
                Map.of(
                        "carNum", carNum.toLowerCase(),
                        "regNum", regNum.toLowerCase()),
                (resultSet, i) -> new Fine(
                        resultSet.getInt("id"),
                        resultSet.getString("carNum"),
                        resultSet.getString("regNum"),
                        resultSet.getString("dateOfFine"),
                        resultSet.getString("info"),
                        resultSet.getInt("price")
                )
        );

    }
}
