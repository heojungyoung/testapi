package com.batch.testapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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



}
