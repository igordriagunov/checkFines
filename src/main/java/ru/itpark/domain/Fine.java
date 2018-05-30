package ru.itpark.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fine {

    private int id;
    private String carNum;
    private String dateOfFine;
    private String info;
    private int price;

}
