package com.example.mylego.ui.parts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mylego.R;

import java.util.ArrayList;

public class PartsAdapter extends RecyclerView.Adapter<PartsAdapter.PartsListItemViewHolder> {
    private ArrayList<PartSingleItem> _partsDataList;

    public static class PartsListItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnailImageView;
        public TextView partNumTextView;
        public TextView partNameTextView;

        public PartsListItemViewHolder(View itemView) {
            super(itemView);
            thumbnailImageView = itemView.findViewById(R.id.imageView);
            partNumTextView = itemView.findViewById(R.id.part_num);
            partNameTextView = itemView.findViewById(R.id.part_name);
        }
    }
    public PartsAdapter(ArrayList<PartSingleItem> partsDataList) {
        _partsDataList = partsDataList;
    }

    @Override
    public PartsListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.part_item_card, parent, false);
        return new PartsListItemViewHolder(listItem);
    }
    @Override
    public void onBindViewHolder(PartsListItemViewHolder holder, int itemPosition) {
        PartSingleItem currentItem = _partsDataList.get(itemPosition);

        holder.partNumTextView.setText(currentItem.getPartNumber());
        holder.partNameTextView.setText(currentItem.getPartName());

        if (currentItem.getThumbnailPath() != null) {
            holder.thumbnailImageView.setImageURI(currentItem.getThumbnailPath());
        } else {
            holder.thumbnailImageView.setImageResource(currentItem.getImageRes());
        }
    }
    @Override
    public int getItemCount() {
        return _partsDataList.size();
    }

}
