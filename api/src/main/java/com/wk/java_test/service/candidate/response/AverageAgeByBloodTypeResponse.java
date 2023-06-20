package com.wk.java_test.service.candidate.response;

public class AverageAgeByBloodTypeResponse {
    private String bloodType;
    private double averageAge;
    public AverageAgeByBloodTypeResponse(String bloodType, double averageAge) {
        this.bloodType = bloodType;
        this.averageAge = averageAge;
    }
    public String getBloodType() {
        return bloodType;
    }
    public double getAverageAge() {
        return averageAge;
    }
}
