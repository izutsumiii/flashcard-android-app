package com.example.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.flashcard.ui.card.CardFlip;
import com.example.flashcard.ui.folders.FolderListActivity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashcard.databinding.ActivityMainBinding;
import com.example.flashcard.ui.wordlist.WordList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button viewFlashcardBtn = findViewById(R.id.viewFlashcardBtn);
        viewFlashcardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FolderListActivity.class);
                startActivity(intent);
            }
        });
    }
}