package com.wk.java_test.service.data;

import com.wk.java_test.ApiExceptionHandler.exceptions.InvalidFileException;
import com.wk.java_test.domain.Candidate;
import com.wk.java_test.mapper.CandidateMapper;
import com.wk.java_test.repository.CandidateRepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class InsertDataService implements IInsertDataService {
    @Autowired
    private CandidateRepository candidateRepository;
    public void insertData(MultipartFile file) throws Exception {
        try {
            String fileContent = new String(file.getBytes());
            JSONArray jsonArray = new JSONArray(fileContent);
            for (int i = 0; i < jsonArray.length(); i++) {
                Candidate newCandidate = CandidateMapper.toEntity(jsonArray.getJSONObject(i));
                candidateRepository.save(newCandidate);
            }
        } catch (Exception e) {
            throw new InvalidFileException("Arquivo inválido!");
        }
    }
}
