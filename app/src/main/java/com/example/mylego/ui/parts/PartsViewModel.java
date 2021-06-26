package com.example.mylego.ui.parts;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mylego.R;
import com.example.mylego.database.DbManager;
import com.example.mylego.database.DbSinglePartsManager;
import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.rest.domain.Part;
import com.example.mylego.ui.Utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PartsViewModel extends AndroidViewModel {




    private final boolean _partsInDbSetsOnly = false;




    private final ArrayList<Part> _partsForAllDbSets;
    private ArrayList<BricksSingleSet> _setsFromDbAll = null;

    private MutableLiveData<ArrayList<Uri>> _thumbnailsPathsList;

    //=== CONSTRUCTORS =============================================================================
    public PartsViewModel(@NonNull Application application) {
        super(application);

        DbManager dbSets = new DbManager(application.getApplicationContext());
        DbSinglePartsManager dbParts = new DbSinglePartsManager(application.getApplicationContext());

        _thumbnailsPathsList = new MutableLiveData<>();

        if (_partsInDbSetsOnly) {
            _setsFromDbAll = dbSets.getAllSets(50);
            if (_setsFromDbAll.size() == 50) {
                _partsForAllDbSets = getPartsForAllSets(dbParts, _setsFromDbAll);
                loadPartsImages();
            }
        } else {
            _partsForAllDbSets = getPartsForAllSets(dbParts);
            loadPartsImages();
        }
    }

    //=== PUBLIC METHODS ===========================================================================
    public ArrayList<BricksSingleSet> getSetsFromDb() {
        return this._setsFromDbAll;
    }

    //==============================================================================================
    public ArrayList<Part> getPartsFromDb() {
        return this._partsForAllDbSets;
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
            _thumbnailsPathsList.postValue(Utils.saveBitmapsToInternalStorage(appContext, result, "PartsImages"));
        }
    }

    private void loadPartsImages() {
        Log.d("SetsView-loadSetsImages", "==> loadSetsImages()");
        URL[] urlsArray = getImagesURLs(_partsForAllDbSets);
        String[] numbersArray = getPartsNumbers(_partsForAllDbSets);

        Map<String, URL> numbersWithURLsAsMap = getPartsNumsWithURLs(numbersArray, urlsArray);

//        for (Map.Entry<String, URL> entry : setsNumWithURLs.entrySet()) {
//            Log.d("SetsView-loadSetsImages", String.format("SetNum as key: %s | SetURL as value: %s", entry.getKey(), entry.getValue()));
//        }
        new AsyncImageDownload().execute(numbersWithURLsAsMap);
    }

    //==============================================================================================
    private Map<String, URL> getPartsNumsWithURLs(String[] setNumbers, URL[] urls) {
        Log.d("SetsView-getSetsNumsWithURLs", String.format("==> Preparing map"));
        Map<String, URL> dataMap = new HashMap<>();
        for (int i = 0; i < urls.length; i++) {
            dataMap.put(setNumbers[i], urls[i]);
        }
        return dataMap;
    }

    //==============================================================================================
    private URL[] getImagesURLs(ArrayList<Part> listOfParts) {
        ArrayList<URL> urlsList = new ArrayList<>();
        for (Part singlePart : listOfParts) {
            Log.d("SetsView-getImagesURLs", String.format("Adding URL: %s", singlePart.getPart_img_url()));
            urlsList.add(Utils.convertStringToURL(singlePart.getPart_img_url()));
        }
        URL[] urls = new URL[ urlsList.size() ];
        return urlsList.toArray(urls);
    }

    //==============================================================================================
    private String[] getPartsNumbers(ArrayList<Part> listOfParts) {
        ArrayList<String> partsNumbersList = new ArrayList<>();
        for (Part singlePart : listOfParts) {
            //Log.d("SetsView-getSetsNumbers", String.format("Adding setNumber: %s", singleSet.getSet_number()));
            partsNumbersList.add(singlePart.getPart_num());
        }
        String[] numbersList = new String[ partsNumbersList.size() ];
        return partsNumbersList.toArray(numbersList);
    }

    //==============================================================================================
    private ArrayList<Part> getPartsForAllSets(DbSinglePartsManager partsDb) {
        return getPartsForAllSets(partsDb, null);
    }
    //==============================================================================================
    private ArrayList<Part> getPartsForAllSets(
            DbSinglePartsManager partsDb,
            ArrayList<BricksSingleSet> setsListFromDb
    ) {
        ArrayList<Part> result = new ArrayList<>();
        ArrayList<Part> allPartsFromSets = new ArrayList<>();
        ArrayList<Part> allPartsFromDb = new ArrayList<>();

        if (setsListFromDb == null) {
            Log.d("PartsView-getPartsForAllSets", String.format("setsListFromDb.isEmpty()"));
            allPartsFromDb = partsDb.getSinglePartsBySetNum(null);

            for (Part partSet : allPartsFromDb) {
                Log.d("PartsView-getPartsForAllSets", String.format("Part from set: %s", partSet.getSet_num()));
            }
            result = allPartsFromDb;
        } else {
            for (BricksSingleSet singleSet : setsListFromDb) {
                String currentSetNum = singleSet.getSet_number();
                Log.d("PartsView-getPartsForAllSets", String.format("Current set: %s", currentSetNum));
                allPartsFromSets = partsDb.getSinglePartsBySetNum(currentSetNum);
            }
            result = allPartsFromSets;
        }
        return result;
    }
}