package com.example.javafxdemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import static com.example.javafxdemo.WebScrape.extractWords;
import static com.example.javafxdemo.WebScrape.getWordFrequency;

public class HelloController implements Initializable {

    @FXML
    private TextField Address;
    @FXML
    private TextField TableName;
    @FXML
    private TextField totalWords;

    @FXML
    private TableView<Words> table;
    @FXML
    private TableColumn<Words, Integer> number;
    @FXML
    private TableColumn<Words, String> words;
    @FXML
    private TableColumn<Words, Integer> Frequency;

    @FXML
    private Button check;
    ObservableList<Words> wordsList = FXCollections.observableArrayList();

    @FXML
    void onCheckButtonClick(ActionEvent event) {

        // HTML table to read
        // URL of the page to fetch
        final String url = Address.getText();
        String tableName = TableName.getText();
        // Maximum words to print
        int wordsMax = Integer.parseInt(totalWords.getText());

        String chapterText = " ";


        try{
            // Connect to the URL and fetch the HTML content
            final Document document = Jsoup.connect(url).get();

            // Find the relevant element with the class name
            Elements chapterElements = document.getElementsByClass(tableName);

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

        //System.out.println("\nTOP 20 WORDS\n");

        // Print the word frequencies
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            breaker++;

            // Words
            System.out.println(entry.getKey() + ": " + entry.getValue());

            wordsList.add(new Words(breaker,entry.getKey(), entry.getValue()));

            // Comment out the if-statement if you want to print all the word
            if (breaker == wordsMax){
                break;
            }
        }


    }


    /**
    * @param url web address
     * @param resourceBundle
    **/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        words.setCellValueFactory(new PropertyValueFactory<>("words"));
        Frequency.setCellValueFactory(new PropertyValueFactory<>("frequency"));

        table.setItems(wordsList);

    }
}
