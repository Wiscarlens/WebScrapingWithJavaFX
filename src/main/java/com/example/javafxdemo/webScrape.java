package com.example.javafxdemo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @author Wiscarlens Lucius
 * Date May 21, 2023
 * */

public class webScrapp {
    public static void main(String[] args) throws FileNotFoundException {
        String chapterText = " ";

        // URL of the page to fetch
        final String url = "https://www.gutenberg.org/files/1065/1065-h/1065-h.htm";

        try{
            // Connect to the URL and fetch the HTML content
            final Document document = Jsoup.connect(url).get();

            // Find the relevant element with the class "chapter"
            Elements chapterElements = document.getElementsByClass("chapter");

            // Iterate over the chapter elements and extract their text
            for (Element chapter : chapterElements) {
                // Data Fetch text holder
                chapterText = chapter.text();
                // TEST: Uncomment this line to print the poem
                //System.out.println(chapterText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Separate each words
        String[] words = extractWords(chapterText);

        Map<String, Integer> frequencyMap = getWordFrequency(words);

        int breaker = 0;

        System.out.println("\nTOP 20 WORDS\n");

        // Print the word frequencies
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            breaker++;

            System.out.println(entry.getKey() + ": " + entry.getValue());

            // Comment out the if-statement if you want to print all the word
            if (breaker == 20){
                break;
            }
        }
    }


    /**
     * @param input a long string of words
     * @return each words as an array of string without the symbol and all lowercase
     * */
    public static String[] extractWords(String input) {
        // Remove symbols and extra spaces
        String cleanedText = input.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ").trim();

        // Convert the cleaned text to lowercase
        String lowercaseText = cleanedText.toLowerCase();

        // Split the lowercase text into an array of words
        String[] words = lowercaseText.split(" ");

        return words;
    }

    /**
     * @param words a long string of words
     *  the function find the frequency of each words
     *  sort the list in ascending order base on frequency
     * @return sortedFrequencyMap a map
     * */
    public static Map<String, Integer> getWordFrequency(String[] words) {
        // Create a map to store word frequencies
        Map<String, Integer> frequencyMap = new HashMap<>();

        // Count the frequency of each word
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Sort the map by value (word frequency) in descending order
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(frequencyMap.entrySet());
        sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Create a new LinkedHashMap to preserve the order of sorted entries
        Map<String, Integer> sortedFrequencyMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : sortedList) {
            sortedFrequencyMap.put(entry.getKey(), entry.getValue());
        }

        return sortedFrequencyMap;
    }
}
