package com.example.mylego.ui.sets;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylego.R;
import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbManager;
import com.example.mylego.rest.domain.BricksSingleSet;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//change from ViewModel to AndroidViewModel for get access to getApplicationContext() method
public class SetsViewModel extends AndroidViewModel {
    //private final ArrayList<BricksSingleSet> _setsFromDbSearch;
    private final ArrayList<BricksSingleSet> _setsFromDbAll;

    //private MutableLiveData<ArrayList<ImageView>> _preloadedImages;
    private final MutableLiveData<String> mText;

    String setNames = "SAMPLE SETS NAMES: ";
    //ArrayList<ImageView> setImages = new ArrayList<>();

    //private AsyncTask<?, ?, ?> downloadImages;

    public SetsViewModel(@NonNull Application application) {
        super(application);

        //read from database
        DbManager db = new DbManager(getApplication().getApplicationContext());
        HashMap<Long, String> setName = db.selectStringQuery(CreateTable.TableEntry.COLUMN_NAME_NAME_STRING, 0, 5);
        for (Map.Entry<Long, String> entry : setName.entrySet()) {
            setNames = setNames.concat(entry.getValue() + ", ");
        }

        //_preloadedImages = new MutableLiveData<>();
        mText = new MutableLiveData<>();

        //_preloadedImages.postValue(setImages);
        mText.postValue(setNames);

        //_setsFromDbSearch = db.getSetsByName("Gears");
        _setsFromDbAll = db.getAllSets(50);

//        if (_setsFromDbAll.size() == 50) {
//            Log.d("SetsView-dbEntries", "50 entries from db");
//            ArrayList<URL> urlsList = new ArrayList<>();
//            for (BricksSingleSet singleSet : _setsFromDbAll) {
//                Log.d("SetsView-dbEntries", String.format("Adding URL: %s", singleSet.getImage_url()));
//                urlsList.add(stringToURL(singleSet.getImage_url()));
//            }
//            URL[] urls = new URL[ urlsList.size() ];
//            urlsList.toArray(urls);
//            new AsyncImageDownload().execute(urls);
//        }
    }

//    public LiveData<ArrayList<ImageView>> getImages() {
//        return _preloadedImages;
//    }
    public LiveData<String> getText() {
        return mText;
    }

//    public ArrayList<BricksSingleSet> getSetsFromDbSearch() {
//        return this._setsFromDbSearch;
//    }
    public ArrayList<BricksSingleSet> getSetsFromDb() {
        return this._setsFromDbAll;
    }

    //== private methods ===========================================================================
//    private final class AsyncImageDownload extends AsyncTask<URL, Void, ArrayList<ImageView>> {
//        ArrayList<ImageView> images = new ArrayList<>();
//
//        @Override
//        protected ArrayList<ImageView> doInBackground(URL... urls) {
//            Log.d("AsyncImageDownload", "Image downloading running");
//            for (URL url : urls) {
//                Log.d("AsyncImageDownload", String.format("Opening URL: %s", url));
//                ImageView downloadedImage = new ImageView(getApplication().getApplicationContext());
//
//                if (url == null) {
//                    Log.d("AsyncImageDownload", "null URL");
//                    downloadedImage.setImageResource(R.drawable.ic_baseline_web_asset_24);
//                    images.add(downloadedImage);
//                } else {
//                    try {
//                        InputStream downloadedImageStream = url.openStream();
//                        Bitmap decodedImage = BitmapFactory.decodeStream(downloadedImageStream);
//                        downloadedImage.setImageBitmap(decodedImage);
//                        images.add(downloadedImage);
//                        downloadedImageStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            return images;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<ImageView> result) {
//            Log.d("AsyncImageDownload", "Images ready, posting...");
//            _preloadedImages.postValue(result);
//        }
//    }
    //==============================================================================================
//    protected URL stringToURL(String urlString) {
//        if (urlString == null) {
//            Log.e("MalformedURL", "URL is NULL");
//        } else {
//            try {
//                return new URL(urlString);
//            } catch (MalformedURLException e) {
//                Log.e("MalformedURL", e.getMessage());
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    public void findAllImageViews(ViewGroup rootView) {
//        ViewGroup root = (ViewGroup) rootView;
//        root.getChildCount();
//    }
}