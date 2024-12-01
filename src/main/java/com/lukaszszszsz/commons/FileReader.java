package com.lukaszszszsz.commons;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public class FileReader {


    public Stream<String> readFile(String fileName) {

        try {
            Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                    .getResource(fileName)).toURI());

            return Files.lines(path);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }
}
