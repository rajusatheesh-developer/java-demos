package com.demos.java.sdk.features;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesNewFeatures {
    public String read(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }
}
