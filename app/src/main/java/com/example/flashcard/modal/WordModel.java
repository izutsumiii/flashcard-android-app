package com.example.flashcard.modal;

public class WordModel {
    private String title;
    private String description;

    public WordModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
