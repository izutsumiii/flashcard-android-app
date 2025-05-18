package com.example.flashcard.modal;

public class WordModel {

    private int folderId;
    private int wordId;
    private String word;
    private String meaning;
    private String imageUri; // 🆕 Add image URI field

    // 🆕 Updated constructor with imageUri
    public WordModel(int folderId, int wordId, String word, String meaning, String imageUri) {
        this.folderId = folderId;
        this.wordId = wordId;
        this.word = word;
        this.meaning = meaning;
        this.imageUri = imageUri;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getImageUri() {
        return imageUri;
    }
}
