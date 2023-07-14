package com.example.javafxdemo;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WebScrapeTest {


    @Test
    void extractWords() {
        var webScrape = new WebScrape();

        String testingInput = "This Is Just a Test";
        String[] testingResult = {"this", "is", "just", "a", "test"};

        assertArrayEquals(testingResult, WebScrape.extractWords(testingInput));
    }

    @Test
    void getWordFrequency() {
        var webScrape = new WebScrape();
        String[] words = {"apple", "banana", "banana", "cherry", "apple", "cherry", "cherry"};

        Map<String, Integer> expectedFrequencyMap = new LinkedHashMap<>();
        expectedFrequencyMap.put("cherry", 3);
        expectedFrequencyMap.put("banana", 2);
        expectedFrequencyMap.put("apple", 2);

        Map<String, Integer> actualFrequencyMap = WebScrape.getWordFrequency(words);

        assertEquals(expectedFrequencyMap, actualFrequencyMap);

    }
}