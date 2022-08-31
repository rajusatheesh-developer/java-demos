package com.demos.java.sdk.features;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static java.nio.file.Paths.get;

public class FilesNewFeatures {
    public String read(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

    public String write(String lines) throws IOException {
        String fileName = "D:\\Java-Int\\Java\\JavaDemos\\files\\%s.txt"
        .formatted(UUID.randomUUID().toString());
        Files.writeString(get(fileName), lines);
        return fileName;
    }
}
