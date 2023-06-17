package com.wk.java_test.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.json.JSONArray;
import org.json.JSONObject;

@RestController()
@RequestMapping("/api/v1/data")
public class InsertDataController {
    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileContent = new String(file.getBytes());
            JSONArray jsonArray = new JSONArray(fileContent);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                System.out.println("Pessoa: " + jsonObject.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body("OK");
    }
}