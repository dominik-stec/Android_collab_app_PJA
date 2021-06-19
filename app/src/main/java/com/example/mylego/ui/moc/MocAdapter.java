package com.example.mylego.ui.moc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mylego.R;

import java.util.ArrayList;

public class MocAdapter extends RecyclerView.Adapter<MocAdapter.ExampleViewHolder> {
    private ArrayList<MocSingleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mMocName;
        public TextView mMocPartsNumber;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mMocName = itemView.findViewById(R.id.moc_set_name);
            mMocPartsNumber = itemView.findViewById(R.id.moc_parts_num);
        }
    }
    public MocAdapter(ArrayList<MocSingleItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public MocAdapter.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.moc_item_card, parent, false);
        MocAdapter.ExampleViewHolder evh = new MocAdapter.ExampleViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(MocAdapter.ExampleViewHolder holder, int position) {
        MocSingleItem currentItem = mExampleList.get(position);
        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mMocName.setText(currentItem.getMocName());
        holder.mMocPartsNumber.setText(currentItem.getMocPartsNumber());
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
