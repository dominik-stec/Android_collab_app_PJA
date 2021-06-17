package com.example.mylego.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import com.example.mylego.rest.domain.BrickLink;
import com.example.mylego.rest.domain.BrickOwl;
import com.example.mylego.rest.domain.Color;
import com.example.mylego.rest.domain.ExternalIdsColor;
import com.example.mylego.rest.domain.ExternalIdsPart;
import com.example.mylego.rest.domain.LDraw;
import com.example.mylego.rest.domain.Lego;
import com.example.mylego.rest.domain.Part;
import com.example.mylego.rest.domain.PartsSingleSet;
import com.example.mylego.rest.domain.Peeron;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class DbPartsManager {

    DbHelper dbHelper = null;

    int id;
    int invPartId;

    Part part = null;
    String partNum;
    String name;
    int partCatId;
    String partUrl;
    String partImgUrl;
    ExternalIdsPart externalIdsPart = null;
    String printOf;

    String[] brickLinkPart = null;
    String[] brickOwlPart = null;
    String[] lDrawPart = null;
    String[] legoPart = null;
    String[] peeronPart = null;


    Color color = null;
    int colorId;
    String colorName;
    String rgb;
    boolean isTrans;
    ExternalIdsColor externalIdsColor = null;

    BrickLink brickLink = null;
    int[] extIdsBrickLink = null;
    String[][] extDescrsBrickLink = null;

    BrickOwl brickOwl = null;
    int[] extIdsBrickOwl = null;
    String[][] extDescrsBrickOwl = null;

    Lego lego = null;
    int[] extIdsLego = null;
    String[][] extDescrsLego = null;

    Peeron peeron = null;
    int[] extIdsPeeron = null;
    String[][] extDescrsPeeron = null;

    LDraw lDraw = null;
    int[] extIdsLDraw = null;
    String[][] extDescrsLDraw = null;

    String setNum;
    int quantity;
    boolean isSpare;
    String elementId;
    int numSets;



    public DbPartsManager(Context context) {
        this.dbHelper = new DbHelper(context);

        Part part = new Part();
        ExternalIdsPart externalIdsPart = new ExternalIdsPart();
        Color color = new Color();
        ExternalIdsColor externalIdsColor = new ExternalIdsColor();
        BrickLink brickLink = new BrickLink();
        BrickOwl brickOwl = new BrickOwl();
        Lego lego = new Lego();
        Peeron peeron = new Peeron();
        LDraw lDraw = new LDraw();

    }



    public long commitIntoDb() {
        // Gets the data repository in write mode
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_ID_INTEGER, id);
        values.put(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_INV_PART_ID_INTEGER, invPartId);
        values.put(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_SET_NUM_STRING, setNum);
        values.put(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_QUANTITY_INTEGER, quantity);
        values.put(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_IS_SPARE_BOOLEAN, isSpare);
        values.put(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_ELEMENT_ID_STRING, elementId);
        values.put(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_NUM_SETS_INTEGER, numSets);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = dbWrite.insert(CreateTable.TableEntryParts.TABLE_NAME_PARTS, null, values);

        return newRowId;
    }

    public ArrayList<PartsSingleSet> getPartsSingleSetBySetNum(String setNum) {

        ArrayList<PartsSingleSet> partsList = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                CreateTable.TableEntryParts.COLUMN_NAME_PARTS_ID_INTEGER,
                CreateTable.TableEntryParts.COLUMN_NAME_PARTS_INV_PART_ID_INTEGER,
                CreateTable.TableEntryParts.COLUMN_NAME_PARTS_SET_NUM_STRING,
                CreateTable.TableEntryParts.COLUMN_NAME_PARTS_QUANTITY_INTEGER,
                CreateTable.TableEntryParts.COLUMN_NAME_PARTS_IS_SPARE_BOOLEAN,
                CreateTable.TableEntryParts.COLUMN_NAME_PARTS_ELEMENT_ID_STRING,
                CreateTable.TableEntryParts.COLUMN_NAME_PARTS_NUM_SETS_INTEGER
        };

        String selection = CreateTable.TableEntryParts.COLUMN_NAME_PARTS_SET_NUM_STRING + "=?";

        String[] selectionArgs = {setNum};

        Cursor cursor = db.query(CreateTable.TableEntryParts.TABLE_NAME_PARTS, projection, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {

            do {
                PartsSingleSet partsSingleSet = new PartsSingleSet();

                partsSingleSet.setId(cursor.getInt(cursor.getColumnIndexOrThrow(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_ID_INTEGER)));
                partsSingleSet.setInvPartId(cursor.getInt(cursor.getColumnIndexOrThrow(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_ID_INTEGER)));
                partsSingleSet.setSetNum(setNum);
                partsSingleSet.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_QUANTITY_INTEGER)));
                boolean isSpare = cursor.getInt(cursor.getColumnIndexOrThrow(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_IS_SPARE_BOOLEAN)) == 1;
                partsSingleSet.setSpare(isSpare);
                partsSingleSet.setElementId(cursor.getString(cursor.getInt(cursor.getColumnIndexOrThrow(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_QUANTITY_INTEGER))));
                partsSingleSet.setNumSets(cursor.getInt(cursor.getInt(cursor.getColumnIndexOrThrow(CreateTable.TableEntryParts.COLUMN_NAME_PARTS_QUANTITY_INTEGER))));

                partsList.add(partsSingleSet);

            } while (cursor.moveToNext());

        }
        return partsList;
    }

    public HashMap<Long, String> selectStringQuery(String columnType, int startId, int endId) {
        HashMap<Long, String> queryResult = new HashMap<Long, String>();

        switch(columnType) {
            case CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_ID_INTEGER:
                throw new IllegalArgumentException("COLUMN_NAME_MINIFIG_ID_INTEGER point into integer values, columnType must point into string type return values from database, change columnType on string point type.");
            case CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_QUANTITY_INTEGER:
                throw new IllegalArgumentException("COLUMN_NAME_MINIFIG_QUANTITY_INTEGER point into integer values, columnType must point into string type return values from database, change columnType on string point type.");
        }

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                columnType,
        };

        Cursor cursor = dbRead.query(
                CreateTable.TableEntryMinifigs.TABLE_NAME_MINIFIG,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {

            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(CreateTable.TableEntryMinifigs._ID));

            if(itemId > endId) break;

            if(itemId >= startId && itemId <= endId) {
                String stringResult = cursor.getString(cursor.getColumnIndexOrThrow(columnType));

                queryResult.put((long)itemId, stringResult);
            }

        }

        cursor.close();
        return queryResult;
    }

    public HashMap<Long, Integer> selectNumberQuery(String columnType, int startId, int endId) {

        HashMap<Long, Integer> queryResult = new HashMap<Long, Integer>();

        switch (columnType) {
            case CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_SET_NUM_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_MINIFIG_SET_NUM_STRING point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
            case CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_SET_NAME_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_MINIFIG_SET_NAME_STRING point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
            case CreateTable.TableEntryMinifigs.COLUMN_NAME_MINIFIG_SET_IMG_URL_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_MINIFIG_SET_IMG_URL_STRING point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");
            case CreateTable.TableEntryMinifigs.COLUMN_NAME_SET_NUM_STRING:
                throw new IllegalArgumentException("COLUMN_NAME_SET_NUM_STRING point into string values, columnType must point into integer type return values from database, change columnType on integer point type.");

        }

        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                columnType,
        };

        Cursor cursor = dbRead.query(
                CreateTable.TableEntryMinifigs.TABLE_NAME_MINIFIG,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,         // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        while (cursor.moveToNext()) {

            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(CreateTable.TableEntryMinifigs._ID));

            if (itemId > endId) break;

            if (itemId >= startId && itemId <= endId) {

                int numberResult = cursor.getInt(cursor.getColumnIndexOrThrow(columnType));

                queryResult.put(itemId, numberResult);
            }

        }

        cursor.close();
        return queryResult;
    }

    public DbHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInv_part_id() {
        return invPartId;
    }

    public void setInv_part_id(int inv_part_id) {
        this.invPartId = inv_part_id;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public String getPartNum() {
        return partNum;
    }

    public void setPartNum(String partNum) {
        this.partNum = partNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartCatId() {
        return partCatId;
    }

    public void setPartCatId(int partCatId) {
        this.partCatId = partCatId;
    }

    public String getPartUrl() {
        return partUrl;
    }

    public void setPartUrl(String partUrl) {
        this.partUrl = partUrl;
    }

    public String getPartImgUrl() {
        return partImgUrl;
    }

    public void setPartImgUrl(String partImgUrl) {
        this.partImgUrl = partImgUrl;
    }

    public ExternalIdsPart getExternalIdsPart() {
        return externalIdsPart;
    }

    public void setExternalIdsPart(ExternalIdsPart externalIdsPart) {
        this.externalIdsPart = externalIdsPart;
    }

    public String getPrintOf() {
        return printOf;
    }

    public void setPrintOf(String printOf) {
        this.printOf = printOf;
    }

    public String[] getBrickLinkPart() {
        return brickLinkPart;
    }

    public void setBrickLinkPart(String[] brickLinkPart) {
        this.brickLinkPart = brickLinkPart;
    }

    public String[] getBrickOwlPart() {
        return brickOwlPart;
    }

    public void setBrickOwlPart(String[] brickOwlPart) {
        this.brickOwlPart = brickOwlPart;
    }

    public String[] getlDrawPart() {
        return lDrawPart;
    }

    public void setlDrawPart(String[] lDrawPart) {
        this.lDrawPart = lDrawPart;
    }

    public String[] getLegoPart() {
        return legoPart;
    }

    public void setLegoPart(String[] legoPart) {
        this.legoPart = legoPart;
    }

    public String[] getPeeronPart() {
        return peeronPart;
    }

    public void setPeeronPart(String[] peeronPart) {
        this.peeronPart = peeronPart;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public boolean isTrans() {
        return isTrans;
    }

    public void setTrans(boolean trans) {
        isTrans = trans;
    }

    public ExternalIdsColor getExternalIdsColor() {
        return externalIdsColor;
    }

    public void setExternalIdsColor(ExternalIdsColor externalIdsColor) {
        this.externalIdsColor = externalIdsColor;
    }

    public BrickLink getBrickLink() {
        return brickLink;
    }

    public void setBrickLink(BrickLink brickLink) {
        this.brickLink = brickLink;
    }

    public int[] getExtIdsBrickLink() {
        return extIdsBrickLink;
    }

    public void setExtIdsBrickLink(int[] extIdsBrickLink) {
        this.extIdsBrickLink = extIdsBrickLink;
    }

    public String[][] getExtDescrsBrickLink() {
        return extDescrsBrickLink;
    }

    public void setExtDescrsBrickLink(String[][] extDescrsBrickLink) {
        this.extDescrsBrickLink = extDescrsBrickLink;
    }

    public BrickOwl getBrickOwl() {
        return brickOwl;
    }

    public void setBrickOwl(BrickOwl brickOwl) {
        this.brickOwl = brickOwl;
    }

    public int[] getExtIdsBrickOwl() {
        return extIdsBrickOwl;
    }

    public void setExtIdsBrickOwl(int[] extIdsBrickOwl) {
        this.extIdsBrickOwl = extIdsBrickOwl;
    }

    public String[][] getExtDescrsBrickOwl() {
        return extDescrsBrickOwl;
    }

    public void setExtDescrsBrickOwl(String[][] extDescrsBrickOwl) {
        this.extDescrsBrickOwl = extDescrsBrickOwl;
    }

    public Lego getLego() {
        return lego;
    }

    public void setLego(Lego lego) {
        this.lego = lego;
    }

    public int[] getExtIdsLego() {
        return extIdsLego;
    }

    public void setExtIdsLego(int[] extIdsLego) {
        this.extIdsLego = extIdsLego;
    }

    public String[][] getExtDescrsLego() {
        return extDescrsLego;
    }

    public void setExtDescrsLego(String[][] extDescrsLego) {
        this.extDescrsLego = extDescrsLego;
    }

    public Peeron getPeeron() {
        return peeron;
    }

    public void setPeeron(Peeron peeron) {
        this.peeron = peeron;
    }

    public int[] getExtIdsPeeron() {
        return extIdsPeeron;
    }

    public void setExtIdsPeeron(int[] extIdsPeeron) {
        this.extIdsPeeron = extIdsPeeron;
    }

    public String[][] getExtDescrsPeeron() {
        return extDescrsPeeron;
    }

    public void setExtDescrsPeeron(String[][] extDescrsPeeron) {
        this.extDescrsPeeron = extDescrsPeeron;
    }

    public LDraw getlDraw() {
        return lDraw;
    }

    public void setlDraw(LDraw lDraw) {
        this.lDraw = lDraw;
    }

    public int[] getExtIdsLDraw() {
        return extIdsLDraw;
    }

    public void setExtIdsLDraw(int[] extIdsLDraw) {
        this.extIdsLDraw = extIdsLDraw;
    }

    public String[][] getExtDescrsLDraw() {
        return extDescrsLDraw;
    }

    public void setExtDescrsLDraw(String[][] extDescrsLDraw) {
        this.extDescrsLDraw = extDescrsLDraw;
    }

    public String getSetNum() {
        return setNum;
    }

    public void setSetNum(String setNum) {
        this.setNum = setNum;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public void setSpare(boolean spare) {
        isSpare = spare;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public int getNumSets() {
        return numSets;
    }

    public void setNumSets(int numSets) {
        this.numSets = numSets;
    }
}
