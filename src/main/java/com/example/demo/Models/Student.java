package com.example.demo.Models;

import com.example.demo.Enum.EGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Student {
    private int id;
    private String name;
    private LocalDate dob;
    private EGender gender;
}
