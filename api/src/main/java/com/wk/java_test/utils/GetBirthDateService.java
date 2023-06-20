package com.wk.java_test.utils;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class GetBirthDateService {
    public Date getDate(String birthDateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
        return java.sql.Date.valueOf(birthDate);
    }
}
