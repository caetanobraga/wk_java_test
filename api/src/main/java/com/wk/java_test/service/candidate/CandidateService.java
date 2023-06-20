package com.wk.java_test.service.candidate;

import com.wk.java_test.ApiExceptionHandler.exceptions.InvalidFileException;
import com.wk.java_test.domain.Candidate;
import com.wk.java_test.mapper.CandidateMapper;
import com.wk.java_test.repository.CandidateRepository;
import com.wk.java_test.service.candidate.response.*;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService implements ICandidateService {
    private static final Integer CEM = 100;
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
    public List<ObesesPrecentageBySexResponse> getObesesPercentageBySex() {
        Long totalObeseMen = candidateRepository.countObeseMen();
        Long totalObeseWomen = candidateRepository.countObeseWomen();
        long totalCandidates = candidateRepository.count();

        double percentMen = (totalObeseMen.doubleValue() / (double) totalCandidates) * CEM;
        double percentWomen = (totalObeseWomen.doubleValue() / (double) totalCandidates) * CEM;

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("0.00", symbols);

        String formattedPercentMen = decimalFormat.format(percentMen);
        String formattedPercentWomen = decimalFormat.format(percentWomen);

        List<ObesesPrecentageBySexResponse> percents = new ArrayList<>();
        percents.add(new ObesesPrecentageBySexResponse("women", Double.parseDouble(formattedPercentWomen)));
        percents.add(new ObesesPrecentageBySexResponse("men", Double.parseDouble(formattedPercentMen)));
        return percents;
    }
    public List<AverageAgeByBloodTypeResponse> getAverageAgeByBloodType() {
        List<Object[]> results = candidateRepository.getAverageAgeByBloodType();
        return results.stream()
                .map(result -> new AverageAgeByBloodTypeResponse((String) result[0], (Double) result[1]))
                .collect(Collectors.toList());
    }
    public List<DonorsByBloodTypeResponse> getDonorsByBloodType() {
        List<Object[]> results = candidateRepository.countDonorsByBloodType();
        return results.stream()
                .map(result -> new DonorsByBloodTypeResponse((String) result[0], (Long) result[1]))
                .collect(Collectors.toList());
    }
}
