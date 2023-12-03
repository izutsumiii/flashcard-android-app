package com.example.flashcard.ui.wordlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.flashcard.R;
import com.example.flashcard.modal.WordModel;

import java.util.ArrayList;
import java.util.List;

public class WordList extends AppCompatActivity {

    private List<WordModel> itemList;
    private RecyclerView recyclerView;
    private Button btnReview, btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();
        itemList.add(new WordModel("Title 1", "Description 1"));
        itemList.add(new WordModel("Title 2", "Description 2"));
        itemList.add(new WordModel("Title 2", "Description 2"));
        itemList.add(new WordModel("Title 2", "Description 2"));
        itemList.add(new WordModel("Title 2", "Description 2"));
        itemList.add(new WordModel("Title 2", "Description 2"));

        WordListAdapter itemAdapter = new WordListAdapter(itemList);
        recyclerView.setAdapter(itemAdapter);

        btnReview = findViewById(R.id.btnReview);
        btnEdit = findViewById(R.id.btnEdit);

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle Review button click
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle Edit button click
            }
        });

        findViewById(R.id.fabAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle add button click
            }
        });
    }
}