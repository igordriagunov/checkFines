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
public class FineRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FineRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Fine> findAllFines() {
        return jdbcTemplate.query(
                "SELECT id, carNum, dateOfFine, info, price FROM fines",
                new RowMapper<Fine>() {
                    @Override
                    public Fine mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new Fine(
                                resultSet.getInt("id"),
                                resultSet.getString("carNum"),
                                resultSet.getString("dateOfFine"),
                                resultSet.getString("info"),
                                resultSet.getInt("price")
                        );
                    }
                }
        );
    }

    public List<Fine> findByCarNumber(String carNum) {
        return jdbcTemplate.query(
                "SELECT id, carNum, dateOfFine, info, price FROM fines WHERE carNum = :carNum",
                Map.of(
                        "carNum", carNum.toLowerCase()),
                (resultSet, i) -> new Fine(
                        resultSet.getInt("id"),
                        resultSet.getString("carNum"),
                        resultSet.getString("dateOfFine"),
                        resultSet.getString("info"),
                        resultSet.getInt("price")
                )
        );

    }
}
