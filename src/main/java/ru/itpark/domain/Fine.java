package ru.itpark.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fines")
public class Fine {
    @Id
    @GeneratedValue
    private int id;
    private String carNum;
    private String regNum;
    private String dateOfFine;
    private String info;
    private int price;

}
