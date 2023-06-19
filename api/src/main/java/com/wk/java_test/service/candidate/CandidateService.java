package com.wk.java_test.service.candidate;

import com.wk.java_test.ApiExceptionHandler.exceptions.InvalidFileException;
import com.wk.java_test.domain.Candidate;
import com.wk.java_test.mapper.CandidateMapper;
import com.wk.java_test.repository.CandidateRepository;
import com.wk.java_test.service.candidate.response.CandidatesByStateResponse;
import com.wk.java_test.service.candidate.response.ImcMediaByAgeGroupResponse;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService implements ICandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    public void insertData(MultipartFile file) throws Exception {
        try {
            String fileContent = new String(file.getBytes());
            JSONArray jsonArray = new JSONArray(fileContent);
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    Candidate newCandidate = CandidateMapper.toEntity(jsonArray.getJSONObject(i));
                    candidateRepository.save(newCandidate);
                }catch (Exception e){
                    System.out.println("Candidato inválido encontrado. Ignorando...");
                }
            }
        } catch (Exception e) {
            throw new InvalidFileException("Arquivo inválido!");
        }
    }
    public List<CandidatesByStateResponse> getCandidatesByState() {
        List<Object[]> candidates = candidateRepository.getCandidatesByState();
        return candidates.stream()
                .map(state -> new CandidatesByStateResponse((String) state[0], (Long) state[1]))
                .collect(Collectors.toList());
    }

    public List<ImcMediaByAgeGroupResponse> getImcMediaByAgeGroup(){
        List<Object[]> imcMedia = candidateRepository.getImcMediaByAgeGroup();
        return imcMedia.stream()
                .map(ageGroup -> new ImcMediaByAgeGroupResponse((Integer) ageGroup[0],(double) ageGroup[1]))
                .collect(Collectors.toList());

    }
}
