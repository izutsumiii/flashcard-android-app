package com.example.flashcard.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcard.R;
import com.example.flashcard.db.DBHandler;
import com.example.flashcard.modal.FolderModal;
import com.example.flashcard.ui.form.FolderFormActivity;

import java.util.ArrayList;

public class FolderListFragment extends Fragment {

    private ArrayList<FolderModal> folderModalArrayList;
    private DBHandler dbHandler;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        folderModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(requireContext());
        folderModalArrayList = dbHandler.getFolderNames();

        RecyclerView recyclerViewDashboard = rootView.findViewById(R.id.recyclerViewDashboard);
        recyclerViewDashboard.setLayoutManager(new LinearLayoutManager(getContext()));

        FolderListAdapter cardAdapter = new FolderListAdapter(folderModalArrayList, requireContext());
        recyclerViewDashboard.setAdapter(cardAdapter);

        Button createFolderButton = rootView.findViewById(R.id.createFolderButton);
        createFolderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFolder();
            }
        });

        return rootView;
    }

    public void createFolder() {
        Intent intent = new Intent(getActivity(), FolderFormActivity.class);
        startActivity(intent);
    }
}