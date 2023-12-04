package com.example.flashcard.modal;

public class FolderModal {
    private int folder_id;
    private String folder_name;

    public String getFolderName(){
        return folder_name;
    }

    public int getFolderId(){
        return folder_id;
    }

    public FolderModal(int folder_id, String folder_name){
        this.folder_id = folder_id;
        this.folder_name = folder_name;
    }
}
