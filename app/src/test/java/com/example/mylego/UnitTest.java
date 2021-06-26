package com.example.mylego;

import android.app.Application;
import android.content.Context;

import com.example.mylego.database.DbManager;
import com.example.mylego.rest.domain.BricksSingleSet;
import com.example.mylego.ui.Utils;

import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class UnitTest {
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