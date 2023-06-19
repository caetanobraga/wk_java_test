package com.wk.java_test.service.data;

import org.springframework.web.multipart.MultipartFile;

public interface IInsertDataService {
    void insertData(MultipartFile file) throws Exception;
}
