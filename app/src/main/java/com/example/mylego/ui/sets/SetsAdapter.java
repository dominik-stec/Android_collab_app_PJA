package com.example.mylego.ui.sets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mylego.R;

import java.util.ArrayList;

public class SetsAdapter extends RecyclerView.Adapter<SetsAdapter.ExampleViewHolder> {

    private ArrayList<SetsSingleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mSetNumAndSetNameTextView;
        public TextView mSetYearTextView;
        public TextView mSetNumPartsTextView;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mSetNumAndSetNameTextView = itemView.findViewById(R.id.set_num_and_set_name);
            mSetYearTextView = itemView.findViewById(R.id.set_year);
            mSetNumPartsTextView = itemView.findViewById(R.id.set_num_parts);
        }
    }
    public SetsAdapter(ArrayList<SetsSingleItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_item_card, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        SetsSingleItem currentItem = mExampleList.get(position);
        //holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mSetNumAndSetNameTextView.setText(String.format("%s %s", currentItem.getSetNum(), currentItem.getSetName()));
        holder.mSetYearTextView.setText(currentItem.getSetYear());
        holder.mSetNumPartsTextView.setText(currentItem.getSetNumParts());
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
