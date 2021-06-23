package com.example.mylego.ui.sets;

import android.app.Application;
import android.content.res.Resources;
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

import java.io.BufferedInputStream;
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

        if (_setsFromDbAll.size() == 50) {
            loadSetsImages();
//            Log.d("SetsView-dbEntries", "50 entries from db");
//
            //new AsyncImageDownload().execute(urls);
        }
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

    //==============================================================================================
    public void loadSetsImages() {
        Log.d("SetsView-loadSetsImages", "==> loadSetsImages()");
        URL[] urlsList = getImagesURLs(_setsFromDbAll);
    }

    //==============================================================================================
    public URL[] getImagesURLs(ArrayList<BricksSingleSet> listOfSets) {
        ArrayList<URL> urlsList = new ArrayList<>();
        for (BricksSingleSet singleSet : listOfSets) {
            Log.d("SetsView-dbEntries", String.format("Adding URL: %s", singleSet.getImage_url()));
            urlsList.add(stringToURL(singleSet.getImage_url()));
        }
        URL[] urls = new URL[ urlsList.size() ];
        return urlsList.toArray(urls);
    }
    //==============================================================================================
    public String[] getSetsNumbers(ArrayList<BricksSingleSet> listOfSets) {
        ArrayList<String> setsNumbersList = new ArrayList<>();
        for (BricksSingleSet singleSet : listOfSets) {
            Log.d("SetsView-dbEntries", String.format("Adding setNumber: %s", singleSet.getSet_number()));
            setsNumbersList.add(singleSet.getSet_number());
        }
        String[] setsNumbers = new String[ setsNumbersList.size() ];
        return setsNumbersList.toArray(setsNumbers);
    }
    //==============================================================================================
//    public Map<String, URL> urlsWithSetsNums(URL[] urls, String[] setNumbers) {
//        Map<String, URL> dataMap = new HashMap<>();
//        for (int i = 0; i < urls.length; i++) {
//
//        }
//    }

    //== private methods ===========================================================================
    private final class AsyncImageDownload extends AsyncTask<URL, Void, ArrayList<Bitmap>> {
        @Override
        protected ArrayList<Bitmap> doInBackground(URL... urls) {
            Log.d("AsyncImageDownload", "Image downloading running");

            ArrayList<Bitmap> bitmaps = new ArrayList<>();

            for (int i = 0; i < urls.length; i++) {
                Log.d("AsyncImageDownload", String.format("Opening URL: %s", urls[i]));

                URL currentUrl = urls[i];
                Bitmap currentBmp = null;

                if (currentUrl == null) {
                    currentBmp = BitmapFactory.decodeResource(
                            getApplication().getResources(),
                            R.drawable.ic_baseline_web_asset_24
                    );
                    bitmaps.add(currentBmp);
                } else {
                    try {
                        InputStream imageToDownload = currentUrl.openStream();
                        BufferedInputStream bufferedImage = new BufferedInputStream(imageToDownload);
                        currentBmp = BitmapFactory.decodeStream(bufferedImage);
                        bitmaps.add(currentBmp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return bitmaps;
        }

        @Override
        protected void onPostExecute(ArrayList<Bitmap> result) {
            Log.d("AsyncImageDownload", "Images ready, posting...");
            saveImagesToInternalStorage(result);
            //_preloadedImages.postValue(result);
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
    //==============================================================================================
    public void saveImagesToInternalStorage(ArrayList<Bitmap> bitmaps) {
        for (Bitmap bmp : bitmaps) {
            saveBitmapToInternalStorage(bmp);
        }
    }
    //==============================================================================================
    public void saveBitmapToInternalStorage(Bitmap bmp) {

    }
//
//    public void findAllImageViews(ViewGroup rootView) {
//        ViewGroup root = (ViewGroup) rootView;
//        root.getChildCount();
//    }
}