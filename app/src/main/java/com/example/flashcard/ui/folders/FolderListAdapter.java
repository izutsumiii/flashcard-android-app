package com.example.flashcard.ui.folders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcard.R;
import com.example.flashcard.db.DBHandler;
import com.example.flashcard.modal.FolderModal;
import com.example.flashcard.ui.form.FolderFormActivity;
import com.example.flashcard.ui.form.WordFormActivity;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_folder_card, parent, false);
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

        public static final int MENU_EDIT = R.id.menu_edit;
        public static final int MENU_DELETE = R.id.menu_delete;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            textTitle = itemView.findViewById(R.id.textTitle);
            ImageView menuIcon = itemView.findViewById(R.id.menuIcon);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = itemView.getContext();
                    saveFolderIdToSharedPreferences(context, folderId);
                    Intent intent = new Intent(context, WordList.class);
                    context.startActivity(intent);
                }
            });

            menuIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showContextMenu(view);
                }
            });
        }

        public void bind(FolderModal folderModal) {
            textTitle.setText(folderModal.getFolderName());
            folderId = folderModal.getFolderId();
            setRandomBackgroundColor();
            int wordCount = getWordCountInAFolder(folderId);
            TextView textWordCount = itemView.findViewById(R.id.totalWords);
            textWordCount.setText(String.valueOf(wordCount) + " words");
        }

        private int getWordCountInAFolder(int folderId) {
            DBHandler dbHandler = new DBHandler(itemView.getContext());
            return dbHandler.getTotalWordsInAFolder(folderId);
        }

        public void showContextMenu(View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.getMenuInflater().inflate(R.menu.context_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    int itemId = item.getItemId();

                    if (itemId == R.id.menu_edit) {
                        editFolder();
                        return true;
                    } else if (itemId == R.id.menu_delete) {
                        deleteFolder();
                        return true;
                    } else {
                        return false;
                    }
                }
            });

            popupMenu.show();
        }

        private void editFolder() {
            Context context = itemView.getContext();
            String folderName = textTitle.getText().toString();
            Intent intent = new Intent(context, FolderFormActivity.class);
            intent.putExtra("folderId", folderId);
            intent.putExtra("folderName", folderName);
            context.startActivity(intent);
        }

        private void deleteFolder() {
            // Implement delete functionality here
            // You can prompt the user for confirmation before deleting
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
            editor.putInt("currentFolderId", folderId);
            editor.apply();
        }
    }
}

