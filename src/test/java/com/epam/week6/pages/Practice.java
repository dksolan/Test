package com.epam.week6.pages;


import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;


public class Practice {

    private static final String tempDir = "/users";

//    public static String filterMultilineStrings(){
//
//        String multilineString = "Baeldung helps \n \n developers \n explore Java.";
//        List<String> lines = multilineString.lines()
//                .filter(line -> !line.isBlank())
//                .map(String::strip)
//                .collect(Collectors.toList());
////        assertThat(lines).containsExactly("Baeldung helps", "developers", "explore Java.");
//        return lines.toString();
//    }

//    public static <T> List<Object> assertThat(final List<T> list) {
//        return Assertions.assertThat(list);
//    }

//    public static void newFileMethods(){
//        Path filePath = null;
//        String fileContent = null;
//        try {
//            filePath = Files.writeString(Files.createTempFile(tempDir, "demo.txt"), "Sample text");
//            fileContent = Files.readString(filePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Assert.assertEquals(fileContent, "Sample text");
//
//    }




    public static void main(String[] args){
//        filterMultilineStrings();
    }
}
