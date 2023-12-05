package com.example.flashcard.ui.card;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.flashcard.R;
import com.example.flashcard.db.DBHandler;
import com.example.flashcard.modal.WordModel;
import com.example.flashcard.ui.wordlist.WordList;

import java.util.ArrayList;

public class CardFlip extends AppCompatActivity {
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;
    private View mCardFrontLayout;
    private View mCardBackLayout;
    private int currentWordIndex;
    private ArrayList<WordModel> wordModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_flip);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViews();
        loadAnimations();
        changeCameraDistance();
        loadWords();
        showWord();

        Button btnPrevious = findViewById(R.id.btnPrevious);
        Button btnNext = findViewById(R.id.btnNext);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPreviousWord();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextWord();
            }
        });
    }

    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mCardFrontLayout.setCameraDistance(scale);
        mCardBackLayout.setCameraDistance(scale);
    }

    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.card_out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.card_in_animation);
    }

    private void findViews() {
        mCardBackLayout = findViewById(R.id.card_back);
        mCardFrontLayout = findViewById(R.id.card_front);
    }

    public void flipCard(View view) {
        if (!mIsBackVisible) {
            mSetRightOut.setTarget(mCardFrontLayout);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
        } else {
            mSetRightOut.setTarget(mCardBackLayout);
            mSetLeftIn.setTarget(mCardFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
        }
    }

    private void loadWords() {
        DBHandler dbHandler = new DBHandler(this);
        int folderId = getFolderIdFromSharedPreferences();
        wordModelArrayList = dbHandler.getWordsByFolderId(folderId);
        currentWordIndex = 0;
    }

    private void showWord() {
        TextView cardTextFront = findViewById(R.id.card_text_front);
        TextView cardTextBack = findViewById(R.id.card_text_back);
        cardTextFront.setText(wordModelArrayList.get(currentWordIndex).getWord());
        cardTextBack.setText(wordModelArrayList.get(currentWordIndex).getMeaning());
        showWordIndex();
    }

    private void showWordIndex() {
        TextView wordIndex = findViewById(R.id.wordIndex);
        int currentIndex = currentWordIndex + 1;
        int totalWords = wordModelArrayList.size();
        String indexText = getString(R.string.index_format, currentIndex, totalWords);
        wordIndex.setText(indexText);
    }

    private int getFolderIdFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("FolderPreferences", MODE_PRIVATE);
        return sharedPreferences.getInt("currentFolderId", -1);
    }

    private void showPreviousWord() {
        if (currentWordIndex > 0) {
            currentWordIndex--;
            showWord();
        }
    }

    private void showNextWord() {
        if (currentWordIndex < wordModelArrayList.size() - 1) {
            currentWordIndex++;
            showWord();
        }
    }
}