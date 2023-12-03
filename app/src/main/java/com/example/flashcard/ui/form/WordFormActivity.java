package com.example.flashcard.ui.form;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashcard.R;
import com.example.flashcard.db.DBHandler;

public class WordFormActivity extends AppCompatActivity {
    private DBHandler dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_form);

        dbhandler = new DBHandler(this);

        Button button = findViewById(R.id.saveButton);
        EditText wordEditText = findViewById(R.id.wordEditText);
        EditText descriptionEditText = findViewById(R.id.descriptionEditText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = wordEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                if (word.isEmpty() || description.isEmpty()) {
                    Toast.makeText(WordFormActivity.this, "Please enter all values", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbhandler.addWord(word, description);
                Toast.makeText(WordFormActivity.this, "Word has been added", Toast.LENGTH_SHORT).show();
                wordEditText.setText("");
                descriptionEditText.setText("");
            }
        });
    }
}