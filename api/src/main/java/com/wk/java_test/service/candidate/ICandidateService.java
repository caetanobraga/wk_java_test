package com.wk.java_test.service.candidate;

import com.wk.java_test.service.candidate.response.CandidatesByStateResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICandidateService {
    void insertData(MultipartFile file) throws Exception;

    List<CandidatesByStateResponse> getCandidatesByState();
}