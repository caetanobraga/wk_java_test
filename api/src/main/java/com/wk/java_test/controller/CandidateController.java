package com.wk.java_test.controller;

import com.wk.java_test.service.candidate.ICandidateService;
import com.wk.java_test.service.candidate.response.CandidatesByStateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/")
public class CandidateController {
    @Autowired
    ICandidateService candidateService;
    @PostMapping("/insert-data")
    public ResponseEntity<?>  insertData(@RequestParam("file") MultipartFile file) throws Exception {
        candidateService.insertData(file);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/get-candidates")
    public ResponseEntity<List<CandidatesByStateResponse>> getCandidates(){
        List<CandidatesByStateResponse> response = candidateService.getCandidatesByState();
        return ResponseEntity.ok().body(response);
    }
}