package com.example.flashcard.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcard.R;
import com.example.flashcard.ui.card.CardAdapter;
import com.example.flashcard.ui.card.CardItem;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        List<CardItem> cardItemList = new ArrayList<>();
        cardItemList.add(new CardItem("IELTS Vocabulary"));
        cardItemList.add(new CardItem("Japanese Days"));
        cardItemList.add(new CardItem("Numbers"));


        RecyclerView recyclerViewDashboard = rootView.findViewById(R.id.recyclerViewDashboard);
        recyclerViewDashboard.setLayoutManager(new LinearLayoutManager(getContext()));

        CardAdapter cardAdapter = new CardAdapter(cardItemList);
        recyclerViewDashboard.setAdapter(cardAdapter);

        return rootView;
    }

}