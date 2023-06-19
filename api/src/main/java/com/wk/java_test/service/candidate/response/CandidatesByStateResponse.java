package com.wk.java_test.service.candidate.response;

public class CandidatesByStateResponse {
    public String state;
    public Long candidatesNumber;

    public CandidatesByStateResponse(String state, Long candidatesNumber){
        this.state = state;
        this.candidatesNumber = candidatesNumber;
    }
}
