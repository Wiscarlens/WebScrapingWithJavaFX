package com.example.javafxdemo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/wordOccurrences";
    private static final String username = "root";
    private static final String password = "Papa@123";

//    public static void main(String[] args) {
//
//        String query = "select * from word";
//
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch(ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            Connection connection = DriverManager.getConnection(url, username, password);
//            Statement statement = connection.createStatement();
//            ResultSet result = statement.executeQuery(query);
//
//            int columnCount = result.getMetaData().getColumnCount();
//
//            while(result.next()) {
//                StringBuilder UniversityData = new StringBuilder();
//                for(int i = 1; i <= columnCount; i++) {
//                    UniversityData.append(result.getString(i)).append(":");
//                }
//                System.out.println(UniversityData);
//
//            }
//
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * Select all data in the database
     * Copy them into an ArrayList
     * @return all dataList*/
    public static List<String> getAllData() {
        List<String> dataList = new ArrayList<>();
        String query = "SELECT * FROM word";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            int columnCount = result.getMetaData().getColumnCount();

            while (result.next()) {
                StringBuilder rowData = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.append(result.getString(i)).append(":");
                }
                dataList.add(rowData.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataList;
    }

    /**
     * @param word searching element
     * @return true if word is in the database
        OR false if word is not in the database
     *
     * */
    public static boolean checkWordExists(String word) {
        String query = "SELECT COUNT(*) FROM word WHERE word = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, word);
            ResultSet result = statement.executeQuery();
            result.next();

            int count = result.getInt(1);
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Return false in case of an exception
    }


    /**
     * @param word each word in the text
     * @param frequency the number of time the word repeated
     * The information will be added to the database
     * */
    public static void addData(String word, int frequency) {
        String query = "INSERT INTO word (word, frequency) VALUES (?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, word);
            statement.setInt(2, frequency);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Data insertion failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
