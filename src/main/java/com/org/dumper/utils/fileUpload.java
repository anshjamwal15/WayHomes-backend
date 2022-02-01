package com.org.dumper.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Data
@Component
public class fileUpload {

    private final Path root = Paths.get("uploads");

    public void init() {
        if (Files.exists(root)) {
            try {
                Files.createDirectory(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Directory already exists");
        }
    }
}
