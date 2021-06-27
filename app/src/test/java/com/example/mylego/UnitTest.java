package com.example.mylego;

import android.app.Application;
import androidx.test.core.app.ApplicationProvider;
import android.content.Context;
import android.net.Uri;

import com.example.mylego.database.DbManager;
import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.ui.Utils;
import com.example.mylego.ui.sets.SetsViewModel;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class UnitTest {
    private Context context = ApplicationProvider.getApplicationContext();

    @Test
    public void convertStringToURL() throws MalformedURLException {
        String providedURL = "https://developer.android.com/";
        URL expectedURL = new URL("https://developer.android.com/");

        assertEquals(expectedURL, Utils.convertStringToURL(providedURL));
    }

//    @Test
//    public void getOneSetFromDbManager() {
//        int expectedCount = 1;
//        int providedLimit = 1;
//        Context context = new Application();
//        DbManager manager = new DbManager(context);
//        assertEquals(expectedCount, manager.getAllSets(providedLimit).size());
//    }

}