package com.arobot.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Robot {
    private String model;
    private String serialNumber;
    private Date manufacturedDate;
    private String category;
}
