package com.example.flashcard.ui.form;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flashcard.R;
import com.example.flashcard.db.DBHandler;

public class FolderFormActivity extends AppCompatActivity {
    private DBHandler dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button createFolderButton = findViewById(R.id.createFolderButton);
        EditText folderNameEditText = findViewById(R.id.folderNameEditText);
        dbhandler = new DBHandler(this);

        createFolderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String folderName = folderNameEditText.getText().toString();

                if(folderName.isEmpty()){
                    Toast.makeText(FolderFormActivity.this, "Please enter folder name", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbhandler.addFolder(folderName);
                Toast.makeText(FolderFormActivity.this, "Folder has been created", Toast.LENGTH_SHORT).show();
                folderNameEditText.setText("");
            }
        });
    }
}