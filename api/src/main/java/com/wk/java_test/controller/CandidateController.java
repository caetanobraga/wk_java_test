package com.wk.java_test.controller;

import com.wk.java_test.service.candidate.ICandidateService;
import com.wk.java_test.service.candidate.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController()
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class CandidateController {
    @Autowired
    ICandidateService candidateService;
    @PostMapping("/candidates")
    public ResponseEntity<?>  insertData(@RequestParam("file") MultipartFile file) throws Exception {
        candidateService.insertData(file);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/candidates")
    public ResponseEntity<List<CandidatesByStateResponse>> getCandidates(){
        List<CandidatesByStateResponse> response = candidateService.getCandidatesByState();
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/media-imc")
    public ResponseEntity<List<ImcMediaByAgeGroupResponse>> getImcMediaByAgeGroup(){
        List<ImcMediaByAgeGroupResponse> response = candidateService.getImcMediaByAgeGroup();
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/obeses-percentage")
    public ResponseEntity<List<ObesesPrecentageBySexResponse>> getObesesPercentageBySex(){
        List<ObesesPrecentageBySexResponse> response = candidateService.getObesesPercentageBySex();
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/average-bloodtype")
    public ResponseEntity<List<AverageAgeByBloodTypeResponse>> getAverageAgeByBloodType(){
        List<AverageAgeByBloodTypeResponse> response = candidateService.getAverageAgeByBloodType();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/donors-by-bloodtype")
    public ResponseEntity<List<DonorsByBloodTypeResponse>> getDonorsByBloodType(){
        List<DonorsByBloodTypeResponse> response = candidateService.getDonorsByBloodType();
        return ResponseEntity.ok().body(response);
    }

}