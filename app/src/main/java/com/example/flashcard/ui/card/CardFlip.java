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
import android.widget.Toast;

import com.example.flashcard.R;
import com.example.flashcard.db.DBHandler;
import com.example.flashcard.modal.WordModel;
import com.example.flashcard.ui.form.WordFormActivity;
import com.example.flashcard.ui.score.ScoreActivity;
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

    private int correctCount = 0;
    private int incorrectCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_flip);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getFolderNameFromSharedPreferences());

        findViews();
        loadAnimations();
        changeCameraDistance();
        loadWords();
        showWord();

        Button btnCorrect = findViewById(R.id.btnCorrect);
        Button btnIncorrect = findViewById(R.id.btnIncorrect);
        btnIncorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incorrectCount++;
                showNextWordOrScore();
            }
        });
        btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctCount++;
                showNextWordOrScore();
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

    public void checkIfFlippedCard(){
        Toast.makeText(CardFlip.this, String.valueOf(!mIsBackVisible), Toast.LENGTH_SHORT).show();
        if (mIsBackVisible) {
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
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.folder_preferences), MODE_PRIVATE);
        return sharedPreferences.getInt(getString(R.string.current_folder_id), -1);
    }

    private String getFolderNameFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.folder_preferences), MODE_PRIVATE);
        return sharedPreferences.getString(getString(R.string.current_folder_name), "Words");
    }

    private void showPreviousWord() {
        if (currentWordIndex > 0) {
            currentWordIndex--;
            showWord();
        }
    }

    private void showNextWordOrScore() {
        if (currentWordIndex < wordModelArrayList.size() - 1) {
            currentWordIndex++;
            checkIfFlippedCard();
            showWord();
        } else {
            showScores();
        }
    }

    private void showScores() {
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra("correctCount", correctCount);
        intent.putExtra("incorrectCount", incorrectCount);
        startActivity(intent);
        finish();
    }
}