package com.example.mylego.ui.sets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mylego.R;

import java.util.ArrayList;

public class SetsAdapter extends RecyclerView.Adapter<SetsAdapter.SetsListItemViewHolder> {
    private ArrayList<SetsSingleItem> _setsDataList;

    public static class SetsListItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnailImageView;
        public TextView setNumAndSetNameTextView;
        public TextView setYearTextView;
        public TextView setNumPartsTextView;

        public SetsListItemViewHolder(View listItem) {
            super(listItem);
            thumbnailImageView = listItem.findViewById(R.id.imageView);
            setNumAndSetNameTextView = listItem.findViewById(R.id.set_num_and_set_name);
            setYearTextView = listItem.findViewById(R.id.set_year);
            setNumPartsTextView = listItem.findViewById(R.id.set_num_parts);
        }
    }
    public SetsAdapter(ArrayList<SetsSingleItem> setsDataList) {
        _setsDataList = setsDataList;
    }

    @Override
    public SetsListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.set_item_card, parent, false);
        return new SetsListItemViewHolder(listItem);
    }
    @Override
    public void onBindViewHolder(SetsListItemViewHolder holder, int itemPosition) {
        SetsSingleItem currentItem = _setsDataList.get(itemPosition);
        holder.setNumAndSetNameTextView.setText(String.format("%s %s", currentItem.getSetNum(), currentItem.getSetName()));
        holder.setYearTextView.setText(currentItem.getSetYear());
        holder.setNumPartsTextView.setText(currentItem.getSetNumParts());

        if (currentItem.getThumbnailPath() != null) {
            holder.thumbnailImageView.setImageURI(currentItem.getThumbnailPath());
        } else {
            holder.thumbnailImageView.setImageResource(currentItem.getImageResource());
        }
    }
    @Override
    public int getItemCount() {
        return _setsDataList.size();
    }
}
