package com.example.flashcard.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcard.R;
import com.example.flashcard.modal.FolderModal;
import com.example.flashcard.ui.wordlist.WordList;

import java.util.ArrayList;
import java.util.Random;

public class FolderListAdapter extends RecyclerView.Adapter<FolderListAdapter.CardViewHolder> {

    private ArrayList<FolderModal> folderModalArrayList;

    private Context context;

    public FolderListAdapter(ArrayList<FolderModal> folderModalArrayList, Context context) {
        this.folderModalArrayList = folderModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        FolderModal folderModal = folderModalArrayList.get(position);
        holder.bind(folderModal);
    }

    @Override
    public int getItemCount() {
        return folderModalArrayList.size();
    }
    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textTitle;
        int folderId;
        Context context;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            textTitle = itemView.findViewById(R.id.textTitle);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = itemView.getContext();
                    saveFolderIdToSharedPreferences(context, folderId);
                    Intent intent = new Intent(context, WordList.class);
                    context.startActivity(intent);
                }
            });
        }

        public void bind(FolderModal folderModal) {
            textTitle.setText(folderModal.getFolderName());
            folderId = folderModal.getFolderId();
            setRandomBackgroundColor();
        }

        private void setRandomBackgroundColor() {
            TypedArray typedArray = itemView.getResources().obtainTypedArray(R.array.android_colors);
            int randomColor = typedArray.getColor(new Random().nextInt(typedArray.length()), 0);
            typedArray.recycle();
            cardView.setCardBackgroundColor(randomColor);
        }

        private void saveFolderIdToSharedPreferences(Context context, int folderId) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("FolderPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Log.i("========>", "folderId : " + folderId);
            editor.putInt("currentFolderId", folderId);
            editor.apply();
        }
    }
}

