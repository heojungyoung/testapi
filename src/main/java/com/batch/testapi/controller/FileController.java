package com.batch.testapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

@RestController
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(FileController.class);

    @GetMapping(value = "/file_read")
    public String getFile() throws IOException {

        Resource resource = new ClassPathResource("fileTest" + File.separator  + "example.txt");

        logger.info("Resource path: {}", resource.getFile().getAbsolutePath());
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                logger.info(line);
            }
        }

        return stringBuilder.toString();
    }



    @GetMapping(value = "/file_upload")
    public String fileUpload() throws IOException {

        String bucketName = "test-sample-00";
        String keyName = "kr/archivo.txt";

        Resource resource = new ClassPathResource("fileTest" + File.separator  + "example.txt");

        Region region = Region.AP_NORTHEAST_2;

        S3Client s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(ProfileCredentialsProvider.create("default"))
                .build();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();

        s3Client.putObject(
                putObjectRequest,
                RequestBody.fromFile(Paths.get(resource.getFile().getAbsolutePath()))
        );

        logger.info("upload a S3 ->  {} / {} ", bucketName , keyName);

        return "ok";
    }


}
