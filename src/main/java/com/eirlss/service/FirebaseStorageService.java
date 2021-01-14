package com.eirlss.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface FirebaseStorageService {
    public String uploadFile(MultipartFile multipartFile) throws IOException;
    public ResponseEntity<Object> downloadFile(String fileName, HttpServletRequest request) throws Exception;

}
