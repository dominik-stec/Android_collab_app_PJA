package com.example.mylego.ui.sets;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mylego.R;
import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbManager;
import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.ui.Utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//change from ViewModel to AndroidViewModel for get access to getApplicationContext() method
public class SetsViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Uri>> _thumbnailsPathsList;
    private final ArrayList<BricksSingleSet> _setsFromDbAll;

    //=== CONSTRUCTORS =============================================================================
    public SetsViewModel(@NonNull Application application) {
        super(application);

        DbManager db = new DbManager(application.getApplicationContext());

        _thumbnailsPathsList = new MutableLiveData<>();

        _setsFromDbAll = db.getAllSets(50);
        if (_setsFromDbAll.size() == 50) {
            loadSetsImages();
        }
    }

    //=== PUBLIC METHODS ===========================================================================
    public ArrayList<BricksSingleSet> getSetsFromDb() {
        return this._setsFromDbAll;
    }

    //==============================================================================================
    public LiveData<ArrayList<Uri>> getThumbnailsPathsList() {
        return this._thumbnailsPathsList;
    }

    //=== PRIVATE METHODS ==========================================================================
    private final class AsyncImageDownload extends AsyncTask<Map<String, URL>, Void, Map<String, Bitmap>> {
        public Context appContext = getApplication().getApplicationContext();

        @Override
        protected Map<String, Bitmap> doInBackground(Map<String, URL>... externalDataMap) {
            Log.d("AsyncImageDownload", "doInBackground running");

            Map<String, URL> dataMap = externalDataMap[0];
            Map<String, Bitmap> resultDataMap = new HashMap<>();

            for (Map.Entry<String, URL> currentSetData : dataMap.entrySet()) {
                //Log.d("AsyncImageDownload", String.format("Processing URL for set: %s", currentSetData.getKey()));

                URL currentUrl = currentSetData.getValue();
                String currentSetNumber = currentSetData.getKey();
                Bitmap currentBmp = null;

                if (currentUrl == null) {
                    currentBmp = Utils.convertVectorDrawableToBmp(appContext, R.drawable.ic_noun_lego_brick_847002);
                } else {
                    try {
                        InputStream imageToDownload = currentUrl.openStream();
                        BufferedInputStream bufferedImage = new BufferedInputStream(imageToDownload);
                        currentBmp = BitmapFactory.decodeStream(bufferedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                resultDataMap.put(currentSetNumber, currentBmp);
            }
            return resultDataMap;
        }

        @Override
        protected void onPostExecute(Map<String, Bitmap> result) {
            Log.d("SetsView-AsyncImageDownload", "Images ready, posting...");
            _thumbnailsPathsList.postValue(Utils.saveBitmapsToInternalStorage(appContext, result, "SetsImages"));
        }
    }

    //==============================================================================================
    private void loadSetsImages() {
        Log.d("SetsView-loadSetsImages", "==> loadSetsImages()");
        URL[] urlsArray = getImagesURLs(_setsFromDbAll);
        String[] setsNumbersArray = getSetsNumbers(_setsFromDbAll);

        Map<String, URL> setsNumWithURLs = getSetsNumsWithURLs(setsNumbersArray, urlsArray);

//        for (Map.Entry<String, URL> entry : setsNumWithURLs.entrySet()) {
//            Log.d("SetsView-loadSetsImages", String.format("SetNum as key: %s | SetURL as value: %s", entry.getKey(), entry.getValue()));
//        }
        new AsyncImageDownload().execute(setsNumWithURLs);
    }

    //==============================================================================================
    private Map<String, URL> getSetsNumsWithURLs(String[] setNumbers, URL[] urls) {
        Log.d("SetsView-getSetsNumsWithURLs", String.format("==> Preparing map"));
        Map<String, URL> dataMap = new HashMap<>();
        for (int i = 0; i < urls.length; i++) {
            dataMap.put(setNumbers[i], urls[i]);
        }
        return dataMap;
    }

    //==============================================================================================
    private URL[] getImagesURLs(ArrayList<BricksSingleSet> listOfSets) {
        ArrayList<URL> urlsList = new ArrayList<>();
        for (BricksSingleSet singleSet : listOfSets) {
            //Log.d("SetsView-getImagesURLs", String.format("Adding URL: %s", singleSet.getImage_url()));
            urlsList.add(Utils.convertStringToURL(singleSet.getImage_url()));
        }
        URL[] urls = new URL[ urlsList.size() ];
        return urlsList.toArray(urls);
    }

    //==============================================================================================
    private String[] getSetsNumbers(ArrayList<BricksSingleSet> listOfSets) {
        ArrayList<String> setsNumbersList = new ArrayList<>();
        for (BricksSingleSet singleSet : listOfSets) {
            //Log.d("SetsView-getSetsNumbers", String.format("Adding setNumber: %s", singleSet.getSet_number()));
            setsNumbersList.add(singleSet.getSet_number());
        }
        String[] setsNumbers = new String[ setsNumbersList.size() ];
        return setsNumbersList.toArray(setsNumbers);
    }
    //==============================================================================================

    //=== SAVED FOR FUTURE, MAY BE USEFUL ==========================================================
//    private final ArrayList<BricksSingleSet> _setsFromDbSearch = new ArrayList<>();
//
//    public ArrayList<BricksSingleSet> getSetsFromDbSearch() {
//        return this._setsFromDbSearch;
//    }
}