package com.example.mylego.ui;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class Utils {
    public Utils() { }

    //==============================================================================================
    public static URL convertStringToURL(String urlString) {
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
    public static Bitmap convertVectorDrawableToBmp(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);

        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888
        );
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    //==============================================================================================
    public static ArrayList<Uri> saveBitmapsToInternalStorage(Context appContext, Map<String, Bitmap> bitmaps, String desiredDirName) {
        ArrayList<Uri> imagesPathsList = new ArrayList<>();
        for (Map.Entry<String, Bitmap> currentBmpSet : bitmaps.entrySet()) {
            String currentSetNum = currentBmpSet.getKey();
            Bitmap currentBmp = currentBmpSet.getValue();

            imagesPathsList.add(Utils.saveBitmapToInternalStorage(
                    appContext,
                    currentSetNum,
                    currentBmp,
                    desiredDirName));
        }
        return imagesPathsList;
    }

    //==============================================================================================
    public static Uri saveBitmapToInternalStorage(Context appContext, String setNum, Bitmap bmp, String desiredDirName) {
        ContextWrapper context = new ContextWrapper(appContext);
        File imageFile = context.getDir(desiredDirName, Context.MODE_PRIVATE);
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

        return Uri.parse(imageFile.getAbsolutePath());
    }
}
