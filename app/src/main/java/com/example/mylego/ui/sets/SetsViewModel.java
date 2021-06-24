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
    //private AsyncTask<?, ?, ?> downloadImages;
    //private final ArrayList<BricksSingleSet> _setsFromDbSearch;
    //ArrayList<ImageView> setImages = new ArrayList<>();
    //private MutableLiveData<ArrayList<ImageView>> _preloadedImages;

    private MutableLiveData<ArrayList<Uri>> _thumbnailsPathsList;
    private final ArrayList<BricksSingleSet> _setsFromDbAll;
    private final MutableLiveData<String> mText;

    String setNames = "SAMPLE SETS NAMES: ";

    public SetsViewModel(@NonNull Application application) {
        super(application);

        //read from database
        DbManager db = new DbManager(getApplication().getApplicationContext());
        HashMap<Long, String> setName = db.selectStringQuery(CreateTable.TableEntry.COLUMN_NAME_NAME_STRING, 0, 5);
        for (Map.Entry<Long, String> entry : setName.entrySet()) {
            setNames = setNames.concat(entry.getValue() + ", ");
        }

        _thumbnailsPathsList = new MutableLiveData<>();
        mText = new MutableLiveData<>();

        mText.postValue(setNames);


        _setsFromDbAll = db.getAllSets(50);
        if (_setsFromDbAll.size() == 50) {
            loadSetsImages();
        }
    }

//    public LiveData<ArrayList<ImageView>> getImages() {
//        return _preloadedImages;
//    }
    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<ArrayList<Uri>> getThumbnailsPathsList() {
        return this._thumbnailsPathsList;
    }

    //==============================================================================================
    public ArrayList<BricksSingleSet> getSetsFromDb() {
        return this._setsFromDbAll;
    }
//    public ArrayList<BricksSingleSet> getSetsFromDbSearch() {
//        return this._setsFromDbSearch;
//    }

    //==============================================================================================
    public void loadSetsImages() {
        Log.d("SetsView-loadSetsImages", "==> loadSetsImages()");
        URL[] urlsArray = getImagesURLs(_setsFromDbAll);
        String[] setsNumbersArray = getSetsNumbers(_setsFromDbAll);

        Map<String, URL> setsNumWithURLs = getSetsNumsWithURLs(setsNumbersArray, urlsArray);

        for (Map.Entry<String, URL> entry : setsNumWithURLs.entrySet()) {
            Log.d("SetsView-loadSetsImages", String.format("SetNum as key: %s | SetURL as value: %s", entry.getKey(), entry.getValue()));
        }
        new AsyncImageDownload().execute(setsNumWithURLs);

    }

    //==============================================================================================
    public URL[] getImagesURLs(ArrayList<BricksSingleSet> listOfSets) {
        ArrayList<URL> urlsList = new ArrayList<>();
        for (BricksSingleSet singleSet : listOfSets) {
            Log.d("SetsView-getImagesURLs", String.format("Adding URL: %s", singleSet.getImage_url()));
            urlsList.add(convertStringToURL(singleSet.getImage_url()));
        }
        URL[] urls = new URL[ urlsList.size() ];
        return urlsList.toArray(urls);
    }
    //==============================================================================================
    public String[] getSetsNumbers(ArrayList<BricksSingleSet> listOfSets) {
        ArrayList<String> setsNumbersList = new ArrayList<>();
        for (BricksSingleSet singleSet : listOfSets) {
            Log.d("SetsView-getSetsNumbers", String.format("Adding setNumber: %s", singleSet.getSet_number()));
            setsNumbersList.add(singleSet.getSet_number());
        }
        String[] setsNumbers = new String[ setsNumbersList.size() ];
        return setsNumbersList.toArray(setsNumbers);
    }
    //==============================================================================================
    public Map<String, URL> getSetsNumsWithURLs(String[] setNumbers, URL[] urls) {
        Log.d("SetsView-getSetsNumsWithURLs", String.format("==> Preparing map"));
        Map<String, URL> dataMap = new HashMap<>();
        for (int i = 0; i < urls.length; i++) {
            dataMap.put(setNumbers[i], urls[i]);
        }
        return dataMap;
    }

    //== private methods ===========================================================================
    private final class AsyncImageDownload extends AsyncTask<Map<String, URL>, Void, Map<String, Bitmap>> {
        public Context appContext = getApplication().getApplicationContext();

        @Override
        protected Map<String, Bitmap> doInBackground(Map<String, URL>... externalDataMap) {
            Log.d("AsyncImageDownload", "doInBackground running");

            Map<String, URL> dataMap = externalDataMap[0];
            Map<String, Bitmap> resultDataMap = new HashMap<>();

            for (Map.Entry<String, URL> currentSetData : dataMap.entrySet()) {
                Log.d("AsyncImageDownload", String.format("Processing URL for set: %s", currentSetData.getKey()));

                URL currentUrl = currentSetData.getValue();
                String currentSetNumber = currentSetData.getKey();
                Bitmap currentBmp = null;

                if (currentUrl == null) {
                    currentBmp = convertToBmp(appContext, R.drawable.ic_baseline_web_asset_24);
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
            Log.d("AsyncImageDownload", "Images ready, posting...");
            _thumbnailsPathsList.postValue(saveBitmapsToInternalStorage(result));

            //_preloadedImages.postValue(result);
        }
    }
    //==============================================================================================
    public Bitmap convertToBmp(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;

    }
    //==============================================================================================
    protected URL convertStringToURL(String urlString) {
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
    public ArrayList<Uri> saveBitmapsToInternalStorage(Map<String, Bitmap> bitmaps) {
        ArrayList<Uri> imagesPathsList = new ArrayList<>();
        for (Map.Entry<String, Bitmap> currentBmpSet : bitmaps.entrySet()) {
            String currentSetNum = currentBmpSet.getKey();
            Bitmap currentBmp = currentBmpSet.getValue();

            imagesPathsList.add(saveBitmapToInternalStorage(currentSetNum, currentBmp));
        }
        return imagesPathsList;
    }
    //==============================================================================================
    public Uri saveBitmapToInternalStorage(String setNum, Bitmap bmp) {
        ContextWrapper context = new ContextWrapper(getApplication().getApplicationContext());
        File imageFile = context.getDir("Images", Context.MODE_PRIVATE);
        imageFile = new File(imageFile, String.format("Thumbnail_%s.jpg", setNum));

        try {
            OutputStream writeToImageFile = null;
            writeToImageFile = new FileOutputStream(imageFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 60, writeToImageFile);
            writeToImageFile.flush();
            writeToImageFile.close();
        } catch (FileNotFoundException e) {
            Log.e("SetsView-saveBitmapToInternalStorage", String.format("FileNotFoundException: %s", e.getMessage()));
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("SetsView-saveBitmapToInternalStorage", String.format("IOException: %s", e.getMessage()));
            e.printStackTrace();
        }

        Uri imageFilePath = Uri.parse(imageFile.getAbsolutePath());
        Log.d("SetsView-saveBitmapToInternalStorage", String.format("Saved image path: %s", imageFilePath));
        return imageFilePath;
    }
}