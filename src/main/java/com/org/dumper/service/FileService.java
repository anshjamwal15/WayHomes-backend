package com.org.dumper.service;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.org.dumper.utils.HelperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Objects;

import static com.org.dumper.config.FirebaseConstants.*;

@AllArgsConstructor
@Service
public class FileService {

    private final HelperUtils helperUtils;

    public void uploadFile(MultipartFile multipartFile, String objectName) throws Exception {

        FileInputStream serviceAccount = new FileInputStream(
                ResourceUtils.getFile("classpath:dumper-firebase.json"));
        File file = helperUtils.multipartFileToFile(multipartFile);
        Path filePath = file.toPath();

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId(FIREBASE_PROJECT_ID).build().getService();
        BlobId blobId = BlobId.of(FIREBASE_BUCKET, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();

        storage.create(blobInfo, Files.readAllBytes(filePath));
    }

}
