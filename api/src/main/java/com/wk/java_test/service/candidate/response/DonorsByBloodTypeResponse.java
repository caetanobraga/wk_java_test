package com.wk.java_test.service.candidate.response;

public class DonorsByBloodTypeResponse {
    private String bloodType;
    private Long donorCount;

    public DonorsByBloodTypeResponse(String bloodType, Long donorCount){
        this.bloodType = bloodType;
        this.donorCount = donorCount;
    }

    public String getBloodType() {
        return bloodType;
    }
    public double getDonorCount() {
        return donorCount;
    }
}
