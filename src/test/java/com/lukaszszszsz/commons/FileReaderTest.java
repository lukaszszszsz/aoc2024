package com.lukaszszszsz.commons;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileReaderTest {

    @Test
    public void givenFile_whenReadFile_stremOfFileLinesIsReturned() {
        FileReader fileReader = new FileReader();
        assertTrue(fileReader.readFile("fileReader.txt").toList().containsAll(List.of("line1","line2","line3","line4")) );
    }

}
