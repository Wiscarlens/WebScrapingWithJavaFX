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

import static com.example.javafxdemo.webScrape.extractWords;
import static com.example.javafxdemo.webScrape.getWordFrequency;

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

//    ObservableList<Words> wordsList = FXCollections.observableArrayList(
//            new Words(1,"Lucius", 20),
//            new Words(2,"John", 5),
//            new Words(3,"Mark", 51),
//            new Words(4,"Lucas", 2),
//            new Words(5,"Johnathan", 57),
//            new Words(6,"Markus", 41)
//    );

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
            //wordsList.add(new Words(2,"John", 5));



            // Comment out the if-statement if you want to print all the word
            if (breaker == wordsMax){
                break;
            }
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        number.setCellValueFactory(new PropertyValueFactory<Words, Integer>("number"));
        words.setCellValueFactory(new PropertyValueFactory<Words, String>("words"));
        Frequency.setCellValueFactory(new PropertyValueFactory<Words, Integer>("frequency"));

        table.setItems(wordsList);

    }
}
