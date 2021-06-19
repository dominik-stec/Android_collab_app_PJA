package com.example.mylego.ui.parts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mylego.R;

import java.util.ArrayList;

public class PartsAdapter extends RecyclerView.Adapter<PartsAdapter.ExampleViewHolder> {

    private ArrayList<PartSingleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mPartNumTextView;
        public TextView mPartNameTextView;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mPartNumTextView = itemView.findViewById(R.id.part_num);
            mPartNameTextView = itemView.findViewById(R.id.part_name);
        }
    }
    public PartsAdapter(ArrayList<PartSingleItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public PartsAdapter.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.part_item_card, parent, false);
        PartsAdapter.ExampleViewHolder evh = new PartsAdapter.ExampleViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(PartsAdapter.ExampleViewHolder holder, int position) {
        PartSingleItem currentItem = mExampleList.get(position);
        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mPartNumTextView.setText(currentItem.getPartNumber());
        holder.mPartNameTextView.setText(currentItem.getPartName());
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

}
