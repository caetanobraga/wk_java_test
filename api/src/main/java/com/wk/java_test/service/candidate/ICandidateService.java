package com.wk.java_test.service.candidate;

import com.wk.java_test.service.candidate.response.CandidatesByStateResponse;
import com.wk.java_test.service.candidate.response.ImcMediaByAgeGroupResponse;
import com.wk.java_test.service.candidate.response.ObesesPrecentageBySexResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICandidateService {
    void insertData(MultipartFile file) throws Exception;

    List<CandidatesByStateResponse> getCandidatesByState();

    List<ImcMediaByAgeGroupResponse> getImcMediaByAgeGroup();

    List<ObesesPrecentageBySexResponse> getObesesPercentageBySex();
}
