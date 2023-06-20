package com.wk.java_test.service.candidate.response;

public class ImcMediaByAgeGroupResponse {
    private int ageGroup;
    private double imcMedia;

    public ImcMediaByAgeGroupResponse(int ageGroup, double imcMedia) {
        this.ageGroup = ageGroup;
        this.imcMedia = imcMedia;
    }
    public int getFaixaIdade() {
        return ageGroup;
    }
    public double getImcMedio() {
        return imcMedia;
    }
}
