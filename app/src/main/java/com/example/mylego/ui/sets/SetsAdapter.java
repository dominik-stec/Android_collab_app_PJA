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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SetsAdapter extends RecyclerView.Adapter<SetsAdapter.ExampleViewHolder> {

    private ArrayList<SetsSingleItem> mExampleList;
    //private AsyncTask<?,?,?> task;

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
        Map<String, ImageView> imageParams = new HashMap<>();
        SetsSingleItem currentItem = mExampleList.get(position);
        imageParams.put(currentItem.getImageUrl(), holder.mImageView);
        new ImageDownloader().execute(imageParams);
        //holder.mImageView.setImageResource(currentItem.getImageResource());
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
        String urlString = null;
        ImageView image = null;
        Bitmap bitmap = null;

        @Override
        protected Bitmap doInBackground(Map<String, ImageView>... params) {
            Log.d("AsyncImageDownload", "==> BackgroudnTask");
            for (Map.Entry<String, ImageView> imageParams : params[0].entrySet()) {
                urlString = imageParams.getKey();
                image = imageParams.getValue();
            }

            imageUrl = stringToURL(urlString);

            Log.d("AsyncImageDownload", String.format("Processing url: %s", imageUrl));

            if (imageUrl == null) {
                Log.d("AsyncImageDownload", "null URL, using default image icon.");
            } else {
                try {
                    bitmap = BitmapFactory.decodeStream(imageUrl.openStream());
                } catch (IOException e) {
                    Log.e("Img-downloader", e.getMessage());
                    e.printStackTrace();
                }
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            Log.d("AsyncImageDownload", "Image downloaded successfully");
            if (imageUrl == null) {
                image.setImageResource(R.drawable.ic_baseline_web_asset_24);
            } else {
                image.setImageBitmap(bitmap);
            }
        }
    }
    //==============================================================================================
    protected URL stringToURL(String urlString) {
        if (urlString == null) {
            Log.e("MalformedURL", "URL is NULL");
        } else {
            try {
                return new URL(urlString);
            } catch (MalformedURLException e) {
                Log.e("MalformedURL", e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }
}
