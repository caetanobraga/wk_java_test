package com.wk.java_test.service.candidate.response;

public class ImcMediaByAgeGroupResponse {
    private int ageGroup;
    private double imcMedia;

    public ImcMediaByAgeGroupResponse(int ageGroup, double imcMedia) {
        this.ageGroup = ageGroup;
        this.imcMedia = imcMedia;
    }
    public int getAgeGroup() {
        return ageGroup;
    }
    public double getImcMedia() {
        return imcMedia;
    }
}
