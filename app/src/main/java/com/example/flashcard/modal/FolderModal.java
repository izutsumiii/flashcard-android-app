package com.example.flashcard.modal;

public class FolderModal {
    private int folder_id;
    private String folder_name;

    public String getFolderName(){
        return folder_name;
    }

    public FolderModal(String folder_name){
        this.folder_name = folder_name;
    }
}
