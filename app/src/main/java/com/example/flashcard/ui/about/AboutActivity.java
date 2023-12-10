package com.example.flashcard.ui.about;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.flashcard.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("About");

        TextView about = findViewById(R.id.textViewDescription);
        String htmlDescription = getString(R.string.app_description);
        about.setText(Html.fromHtml(htmlDescription));
    }
}