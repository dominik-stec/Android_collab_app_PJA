package com.example.mylego.ui.sets;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mylego.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import retrofit2.http.Url;

public class SetsAdapter extends RecyclerView.Adapter<SetsAdapter.ExampleViewHolder> {

    private ArrayList<SetsSingleItem> mExampleList;
    private AsyncTask<?,?,?> task;

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
        //Map<String, ImageView> imageParams = new HashMap<>();
        SetsSingleItem currentItem = mExampleList.get(position);
        //imageParams.put(currentItem.getImageResource(), holder.mImageView);
        //new ImageDownloader().execute(imageParams);
        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mSetNumAndSetNameTextView.setText(String.format("%s %s", currentItem.getSetNum(), currentItem.getSetName()));
        holder.mSetYearTextView.setText(currentItem.getSetYear());
        holder.mSetNumPartsTextView.setText(currentItem.getSetNumParts());
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    //==============================================================================================
    private final class ImageDownloader extends AsyncTask<Map<String, ImageView>, Void, Bitmap> {
        URL imageUrl = null;
        ImageView bitmap;

        @Override
        protected Bitmap doInBackground(Map<String, ImageView>... params) {
            for (Map.Entry<String, ImageView> entry : params[0].entrySet()) {
                try {
                    imageUrl = new URL(entry.getKey());
                } catch (MalformedURLException e) {
                    Log.e("Img-Downloader", e.getMessage());
                    e.printStackTrace();
                }
                bitmap = entry.getValue();
            }

            Bitmap resultImage = null;
            try {
                int retry = 0;
                InputStream is = null;
                do {
                    is = imageUrl.openStream();
                    retry++;
                } while (is == null || retry == 4);

                if (is == null) {
                    Log.e("Img-downloader", "URL input stream == null");
                }

                resultImage = BitmapFactory.decodeStream(imageUrl.openStream());

            } catch (IOException e) {
                Log.e("Img-downloader", e.getMessage());
                e.printStackTrace();
            }
            return resultImage;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            bitmap.setImageBitmap(result);
        }
    }
}
