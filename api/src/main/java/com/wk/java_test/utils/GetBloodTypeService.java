package com.wk.java_test.utils;

import com.wk.java_test.domain.BloodType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
@Service
public class GetBloodTypeService {
    public BloodType getType(String bloodTypeString){
        for (BloodType bloodType : BloodType.values()) {
            if (bloodType.getValue().equalsIgnoreCase(bloodTypeString)) {
                return bloodType;
            }
        }
        return null;
    }
}

