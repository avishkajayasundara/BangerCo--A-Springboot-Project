package com.eirlss.service.impl;

import com.eirlss.service.FirebaseStorageService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Objects;

@Service
@Transactional
public class FirebaseStorageServiceImpl implements FirebaseStorageService {
    private final Logger log = LoggerFactory.getLogger(FirebaseStorageServiceImpl.class);
    private final Environment environment;
    private Storage storage;
    private String bucketName;
    private String projectId;
    public FirebaseStorageServiceImpl(Environment environment) {
        this.environment = environment;

    }

    @PostConstruct
    private void initializeFirebase() throws Exception {
        bucketName = "bangerco-f1385.appspot.com";
        FileInputStream serviceAccount =
                new FileInputStream("C://Users//User//Desktop\\EIRLS\\BangerCoEirlss\\src\\main\\resources\\firebase\\bangerco-f1385-firebase-adminsdk-vyfce-faa4927810.json");

        this.storage = StorageOptions.newBuilder()
                .setProjectId("bangerco-f1385")
                .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build().getService();;

        serviceAccount =
                new FileInputStream("C://Users//User//Desktop\\EIRLS\\BangerCoEirlss\\src\\main\\resources\\firebase\\bangerco-f1385-firebase-adminsdk-vyfce-faa4927810.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        File file = convertMultiPartToFile(multipartFile);
        Path filePath = file.toPath();
        String objectName = generateFileName(multipartFile);

        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, Files.readAllBytes(filePath));
        log.info("File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
        return blob.getMediaLink();
    }


    @Override
    public ResponseEntity<Object> downloadFile(String fileName, HttpServletRequest request) throws Exception {

        Blob blob = storage.get(BlobId.of("bangerco-f1385.appspot.com", fileName));
        ReadChannel reader = blob.reader();
        InputStream inputStream = Channels.newInputStream(reader);

        byte[] content = null;
        log.info("File downloaded successfully.");
        content = IOUtils.toByteArray(inputStream);

        final ByteArrayResource byteArrayResource = new ByteArrayResource(content);

        return ResponseEntity
                .ok()
                .contentLength(content.length)
                .header("Content-type", "application/octet-stream")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(byteArrayResource);

    }


    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
    }
}
