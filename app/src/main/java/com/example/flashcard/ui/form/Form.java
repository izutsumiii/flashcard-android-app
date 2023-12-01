package com.example.flashcard.ui.form;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flashcard.R;
import com.example.flashcard.db.DBHandler;


public class Form extends Fragment {


    private DBHandler dbhandler;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);

        Button button = view.findViewById(R.id.saveButton);
        EditText wordEditText = view.findViewById(R.id.wordEditText);
        EditText descriptionEditText = view.findViewById(R.id.descriptionEditText);

        dbhandler = new DBHandler(requireContext());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = wordEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                if(word.isEmpty() || description.isEmpty()){
                    Toast.makeText(requireContext(), "Please enter all values", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbhandler.addWord(word,description);
                Toast.makeText(requireContext(), "Word has been added", Toast.LENGTH_SHORT).show();
                wordEditText.setText("");
                descriptionEditText.setText("");
            }
        });

        return view;
    }
}