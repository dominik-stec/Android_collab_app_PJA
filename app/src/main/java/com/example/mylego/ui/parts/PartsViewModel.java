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
import com.example.mylego.database.CreateTable;
import com.example.mylego.database.DbManager;
import com.example.mylego.database.DbPartsManager;
import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.rest.domain.PartsSingleSet;
import com.example.mylego.ui.Utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PartsViewModel extends AndroidViewModel {

    private ArrayList<BricksSingleSet> _setsFromDbAll = null;
    private Map<String, ArrayList<PartsSingleSet>> _partsForAllDbSets;
    private boolean _partsInDbSetsOnly = false;

    private MutableLiveData<ArrayList<Uri>> _thumbnailsPathsList;

    //=== CONSTRUCTORS =============================================================================
    public PartsViewModel(@NonNull Application application) {
        super(application);

        DbManager dbSets = new DbManager(application.getApplicationContext());
        DbPartsManager dbParts = new DbPartsManager(application.getApplicationContext());

        if (_partsInDbSetsOnly) {
            _setsFromDbAll = dbSets.getAllSets(50);
            if (_setsFromDbAll.size() == 50) {
                _partsForAllDbSets = getPartsForAllSets(dbParts, _setsFromDbAll);
            }
        } else {
            _partsForAllDbSets = getPartsForAllSets(dbParts);
        }
    }

    //=== PUBLIC METHODS ===========================================================================
    public ArrayList<BricksSingleSet> getSetsFromDb() {
        return this._setsFromDbAll;
    }

    //==============================================================================================
    public Map<String, ArrayList<PartsSingleSet>> getPartsForAllDbSets() {
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
                    currentBmp = Utils.convertVectorDrawableToBmp(appContext, R.drawable.ic_baseline_web_asset_24);
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
            //_thumbnailsPathsList.postValue(Utils.saveBitmapsToInternalStorage(appContext, result, "PartsImages"));
        }
    }

    //==============================================================================================
    private Map<String, ArrayList<PartsSingleSet>> getPartsForAllSets(DbPartsManager partsDb) {
        return getPartsForAllSets(partsDb, null);
    }
    //==============================================================================================
    private Map<String, ArrayList<PartsSingleSet>> getPartsForAllSets(
            DbPartsManager partsDb,
            ArrayList<BricksSingleSet> setsListFromDb
    ) {
        Map<String, ArrayList<PartsSingleSet>> result = new HashMap<>();
        Map<String, ArrayList<PartsSingleSet>> allPartsFromSets = new HashMap<>();
        ArrayList<PartsSingleSet> allPartsFromDb = new ArrayList<>();

        if (setsListFromDb == null) {
            Log.d("PartsView-getPartsForAllSets", String.format("setsListFromDb.isEmpty()"));
            allPartsFromDb = partsDb.getPartsSingleSetBySetNum(null);

            for (PartsSingleSet partsSet : allPartsFromDb) {
                Log.d("PartsView-getPartsForAllSets", String.format("Part from set: %s", partsSet.getSet_num()));
            }
            result.put("All-Parts", allPartsFromDb);
        } else {
            for (BricksSingleSet singleSet : setsListFromDb) {
                String currentSetNum = singleSet.getSet_number();
                Log.d("PartsView-getPartsForAllSets", String.format("Current set: %s", currentSetNum));
                allPartsFromSets.put(currentSetNum, partsDb.getPartsSingleSetBySetNum(currentSetNum));
            }
            result = allPartsFromSets;
        }
        return result;
    }
}