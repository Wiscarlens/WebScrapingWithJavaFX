package com.example.javafxdemo;

public class Words {
    private int number;
    private final String words;
    private final int frequency;

    public Words(int number, String words, int frequency) {
        this.number = number;
        this.words = words;
        this.frequency = frequency;
    }

    public Words(String words, int frequency) {
        this.words = words;
        this.frequency = frequency;
    }

    public int getNumber() {
        return number;
    }

    public String getWords() {
        return words;
    }

    public int getFrequency() {
        return frequency;
    }
}
