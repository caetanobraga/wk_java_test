package com.wk.java_test.controller;

import com.wk.java_test.service.data.IInsertDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController()
@RequestMapping("/api/v1/data")
public class InsertDataController {
    @Autowired
    IInsertDataService insertDataService;
    @PostMapping("/insert-data")
    public ResponseEntity<?>  insertData(@RequestParam("file") MultipartFile file) {
        insertDataService.insertData(file);
        return ResponseEntity.ok().build();
    }
}