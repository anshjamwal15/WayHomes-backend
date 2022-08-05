package com.org.dumper.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@AllArgsConstructor
@Service
public class FileService {

    private final AmazonS3 s3Client;

    public void uploadFile(MultipartFile file) {

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setLastModified(new Date());
        try {
            s3Client.putObject(
                    new PutObjectRequest("dumper-storage", file.getName(), file.getInputStream(), metadata)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] download(String path, String key) {
        try {
            S3Object object = s3Client.getObject(path, key);
            S3ObjectInputStream objectContent = object.getObjectContent();
            return IOUtils.toByteArray(objectContent);
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download the file", e);
        }
    }
}
