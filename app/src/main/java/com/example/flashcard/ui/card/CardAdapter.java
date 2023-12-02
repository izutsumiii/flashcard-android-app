package com.example.flashcard.ui.card;

import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcard.R;

import java.util.List;
import java.util.Random;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<CardItem> cardItemList;

    public CardAdapter(List<CardItem> cardItemList) {
        this.cardItemList = cardItemList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardItem cardItem = cardItemList.get(position);
        holder.bind(cardItem);
    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }
    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textTitle;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            textTitle = itemView.findViewById(R.id.textTitle);
        }

        public void bind(CardItem cardItem) {
            textTitle.setText(cardItem.getTitle());
            setRandomBackgroundColor();
        }

        private void setRandomBackgroundColor() {
            TypedArray typedArray = itemView.getResources().obtainTypedArray(R.array.android_colors);
            int randomColor = typedArray.getColor(new Random().nextInt(typedArray.length()), 0);
            typedArray.recycle();
            cardView.setCardBackgroundColor(randomColor);
        }
    }
}

