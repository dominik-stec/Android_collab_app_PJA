package com.example.mylego.database;

import android.provider.BaseColumns;

public final class CreateTable {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private CreateTable() {}

    /* Inner class that defines the table contents */
    public static class TableEntry implements BaseColumns {
        public static final String TABLE_NAME = "brick_set";
        public static final String COLUMN_NAME_SET_NUM_STRING = "set_number";
        public static final String COLUMN_NAME_NAME_STRING = "name";
        public static final String COLUMN_NAME_YEAR_INTEGER = "year";
        public static final String COLUMN_NAME_THEME_ID_INTEGER = "theme_id";
        public static final String COLUMN_NAME_NUM_PARTS_INTEGER = "number_of_parts";
        public static final String COLUMN_NAME_SET_IMG_URL_STRING = "image_url";
        public static final String COLUMN_NAME_SET_URL_STRING= "set_url";
        public static final String COLUMN_NAME_LAST_MODIFIED_DT_STRING = "modification_date";
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TableEntry.TABLE_NAME + " (" +
                    TableEntry._ID + " INTEGER PRIMARY KEY," +
                    TableEntry.COLUMN_NAME_SET_NUM_STRING + " TEXT," +
                    TableEntry.COLUMN_NAME_NAME_STRING + " TEXT," +
                    TableEntry.COLUMN_NAME_YEAR_INTEGER + " INTEGER," +
                    TableEntry.COLUMN_NAME_THEME_ID_INTEGER + " INTEGER," +
                    TableEntry.COLUMN_NAME_NUM_PARTS_INTEGER + " INTEGER," +
                    TableEntry.COLUMN_NAME_SET_IMG_URL_STRING + " TEXT," +
                    TableEntry.COLUMN_NAME_SET_URL_STRING + " TEXT," +
                    TableEntry.COLUMN_NAME_LAST_MODIFIED_DT_STRING + " TEXT)";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TableEntry.TABLE_NAME;



    /* Inner class that defines the table contents */
    public static class TableEntryMinifigs implements BaseColumns {
        public static final String TABLE_NAME_MINIFIG = "minifig";
        public static final String COLUMN_NAME_MINIFIG_ID_INTEGER = "id";
        public static final String COLUMN_NAME_SET_NUM_STRING = "set_num_contain";
        public static final String COLUMN_NAME_MINIFIG_SET_NUM_STRING = "set_num";
        public static final String COLUMN_NAME_MINIFIG_SET_NAME_STRING = "set_name";
        public static final String COLUMN_NAME_MINIFIG_QUANTITY_INTEGER = "quantity";
        public static final String COLUMN_NAME_MINIFIG_SET_IMG_URL_STRING = "set_img_url";
    }

    public static final String SQL_CREATE_TABLE_MINIFIGS =
            "CREATE TABLE " + TableEntryMinifigs.TABLE_NAME_MINIFIG + " (" +
                    TableEntryMinifigs._ID + " INTEGER PRIMARY KEY," +
                    TableEntryMinifigs.COLUMN_NAME_SET_NUM_STRING + " TEXT," +
                    TableEntryMinifigs.COLUMN_NAME_MINIFIG_ID_INTEGER + " INTEGER," +
                    TableEntryMinifigs.COLUMN_NAME_MINIFIG_SET_NUM_STRING + " TEXT," +
                    TableEntryMinifigs.COLUMN_NAME_MINIFIG_SET_NAME_STRING + " INTEGER," +
                    TableEntryMinifigs.COLUMN_NAME_MINIFIG_QUANTITY_INTEGER + " INTEGER," +
                    TableEntryMinifigs.COLUMN_NAME_MINIFIG_SET_IMG_URL_STRING + " TEXT)";

    public static final String SQL_DELETE_TABLE_MINIFIGS =
            "DROP TABLE IF EXISTS " + TableEntryMinifigs.TABLE_NAME_MINIFIG;


    public static class TableEntrySetNum implements BaseColumns {
        public static final String TABLE_NAME_SETNUM = "set_num";
        public static final String COLUMN_NAME_SETNUM_SET_NUM_STRING = "set_num";
        public static final String COLUMN_NAME_SETNUM_ID = "_id";
    }

    public static final String SQL_CREATE_TABLE_SETNUM =
            "CREATE TABLE " + TableEntrySetNum.TABLE_NAME_SETNUM + " (" +
                    TableEntrySetNum._ID + " INTEGER PRIMARY KEY," +
                    TableEntrySetNum.COLUMN_NAME_SETNUM_SET_NUM_STRING + " TEXT)";


    public static final String SQL_DELETE_TABLE_SETNUM =
            "DROP TABLE IF EXISTS " + TableEntrySetNum.TABLE_NAME_SETNUM;



    /* Inner class that defines the table contents */
    public static class TableEntryParts implements BaseColumns {
        public static final String TABLE_NAME_PARTS = "parts";
        public static final String COLUMN_NAME_PARTS_ID_INTEGER = "id";
        public static final String COLUMN_NAME_PARTS_INV_PART_ID_INTEGER = "inv_part_id";
        public static final String COLUMN_NAME_PARTS_PART_NUM_STRING = "part_num";
        public static final String COLUMN_NAME_PARTS_NAME_STRING = "name";
        public static final String COLUMN_NAME_PARTS_PART_CAT_ID_INTEGER = "part_cat_id";
        public static final String COLUMN_NAME_PARTS_PART_URL_STRING = "part_url";
        public static final String COLUMN_NAME_PARTS_PART_IMG_URL_STRING = "part_img_url";
        public static final String COLUMN_NAME_PARTS_PRINT_OF_STRING = "print_of";
        public static final String COLUMN_NAME_PARTS_SET_NUM_STRING = "set_num";
        public static final String COLUMN_NAME_PARTS_QUANTITY_INTEGER = "quantity";
        public static final String COLUMN_NAME_PARTS_IS_SPARE_BOOLEAN = "is_spare";
        public static final String COLUMN_NAME_PARTS_ELEMENT_ID_STRING = "element_id";
        public static final String COLUMN_NAME_PARTS_NUM_SETS_INTEGER = "num_sets";
    }

    public static final String SQL_CREATE_TABLE_PARTS =
            "CREATE TABLE " + TableEntryParts.TABLE_NAME_PARTS + " (" +
                    TableEntryParts._ID + " INTEGER PRIMARY KEY," +
                    TableEntryParts.COLUMN_NAME_PARTS_ID_INTEGER + " TEXT," +
                    TableEntryParts.COLUMN_NAME_PARTS_INV_PART_ID_INTEGER + " INTEGER," +
                    TableEntryParts.COLUMN_NAME_PARTS_PART_NUM_STRING + " TEXT," +
                    TableEntryParts.COLUMN_NAME_PARTS_NAME_STRING + " TEXT," +
                    TableEntryParts.COLUMN_NAME_PARTS_PART_CAT_ID_INTEGER + " INTEGER," +
                    TableEntryParts.COLUMN_NAME_PARTS_PART_URL_STRING + " TEXT," +
                    TableEntryParts.COLUMN_NAME_PARTS_PART_IMG_URL_STRING + " TEXT," +
                    TableEntryParts.COLUMN_NAME_PARTS_PRINT_OF_STRING + " TEXT," +
                    TableEntryParts.COLUMN_NAME_PARTS_SET_NUM_STRING + " TEXT," +
                    TableEntryParts.COLUMN_NAME_PARTS_QUANTITY_INTEGER + " INTEGER," +
                    TableEntryParts.COLUMN_NAME_PARTS_IS_SPARE_BOOLEAN + " INTEGER," +
                    TableEntryParts.COLUMN_NAME_PARTS_ELEMENT_ID_STRING + " TEXT," +
                    TableEntryParts.COLUMN_NAME_PARTS_NUM_SETS_INTEGER + " INTEGER)";

    public static final String SQL_DELETE_TABLE_PARTS =
            "DROP TABLE IF EXISTS " + TableEntryParts.TABLE_NAME_PARTS;

    public static class TableEntryPart implements BaseColumns {
        public static final String TABLE_NAME_PART = "part";
        public static final String COLUMN_NAME_PART_SET_NUM_STRING = "set_num";
        public static final String COLUMN_NAME_PART_NAME_STRING = "name";
        public static final String COLUMN_NAME_PART_PART_CAT_ID_INTEGER = "part_cat_id";
        public static final String COLUMN_NAME_PART_PART_URL_STRING = "part_url";
        public static final String COLUMN_NAME_PART_PART_IMG_URL_STRING = "part_img_url";
        public static final String COLUMN_NAME_PART_PRINT_OF_STRING = "print_of";
    }

    public static final String SQL_CREATE_TABLE_PART =
            "CREATE TABLE " + TableEntryPart.TABLE_NAME_PART + " (" +
                    TableEntryPart._ID + " INTEGER PRIMARY KEY," +
                    TableEntryPart.COLUMN_NAME_PART_SET_NUM_STRING + " TEXT," +
                    TableEntryPart.COLUMN_NAME_PART_NAME_STRING + " INTEGER," +
                    TableEntryPart.COLUMN_NAME_PART_PART_CAT_ID_INTEGER + " INTEGER," +
                    TableEntryPart.COLUMN_NAME_PART_PART_URL_STRING + " TEXT," +
                    TableEntryPart.COLUMN_NAME_PART_PART_IMG_URL_STRING + " TEXT," +
                    TableEntryPart.COLUMN_NAME_PART_PRINT_OF_STRING + " TEXT)";

    public static final String SQL_DELETE_TABLE_PART =
            "DROP TABLE IF EXISTS " + TableEntryPart.TABLE_NAME_PART;

    public static class TableEntryColor implements BaseColumns {
        public static final String TABLE_NAME_COLOR = "color";
        public static final String COLUMN_NAME_COLOR_SET_NUM_STRING = "set_num";
        public static final String COLUMN_NAME_COLOR_COLOR_ID_INTEGER = "color_id";
        public static final String COLUMN_NAME_COLOR_COLOR_NAME_STRING = "color_name";
        public static final String COLUMN_NAME_COLOR_RGB_STRING = "rgb";
        public static final String COLUMN_NAME_COLOR_IS_TRANS_BOOLEAN = "is_trans";
    }

    public static final String SQL_CREATE_TABLE_COLOR =
            "CREATE TABLE " + TableEntryColor.TABLE_NAME_COLOR + " (" +
                    TableEntryColor._ID + " INTEGER PRIMARY KEY," +
                    TableEntryColor.COLUMN_NAME_COLOR_SET_NUM_STRING + " TEXT," +
                    TableEntryColor.COLUMN_NAME_COLOR_COLOR_ID_INTEGER + " INTEGER," +
                    TableEntryColor.COLUMN_NAME_COLOR_COLOR_NAME_STRING + " INTEGER," +
                    TableEntryColor.COLUMN_NAME_COLOR_RGB_STRING + " TEXT," +
                    TableEntryColor.COLUMN_NAME_COLOR_IS_TRANS_BOOLEAN + " TEXT)";

    public static final String SQL_DELETE_TABLE_COLOR =
            "DROP TABLE IF EXISTS " + TableEntryColor.TABLE_NAME_COLOR;


    public static class TableEntryPartsSubtablesPart implements BaseColumns {
        public static final String TABLE_NAME_SUBTABLES_PART = "subtables_part";
        public static final String COLUMN_NAME_SUBTABLES_PART_BRICKS_LINK_STRING = "set_num";
        public static final String COLUMN_NAME_SUBTABLES_PART_COLOR_ID_INTEGER = "color_id";
        public static final String COLUMN_NAME_SUBTABLES_PART_COLOR_NAME_STRING = "color_name";
        public static final String COLUMN_NAME_SUBTABLES_PART_RGB_STRING = "rgb";
        public static final String COLUMN_NAME_SUBTABLES_PART_IS_TRANS_BOOLEAN = "is_trans";
    }

    public static final String SQL_CREATE_TABLE_SUBTABLES_PART =
            "CREATE TABLE " + TableEntryPartsSubtablesPart.TABLE_NAME_SUBTABLES_PART + " (" +
                    TableEntryPartsSubtablesPart._ID + " INTEGER PRIMARY KEY," +
                    TableEntryPartsSubtablesPart.COLUMN_NAME_SUBTABLES_PART_BRICKS_LINK_STRING + " TEXT," +
                    TableEntryPartsSubtablesPart.COLUMN_NAME_SUBTABLES_PART_COLOR_ID_INTEGER + " INTEGER," +
                    TableEntryPartsSubtablesPart.COLUMN_NAME_SUBTABLES_PART_COLOR_NAME_STRING + " TEXT," +
                    TableEntryPartsSubtablesPart.COLUMN_NAME_SUBTABLES_PART_RGB_STRING + " TEXT," +
                    TableEntryPartsSubtablesPart.COLUMN_NAME_SUBTABLES_PART_IS_TRANS_BOOLEAN + " INTEGER)";

    public static final String SQL_DELETE_TABLE_SUBTABLES_PART =
            "DROP TABLE IF EXISTS " + TableEntryPartsSubtablesPart.TABLE_NAME_SUBTABLES_PART;


    public static class TableEntryPartsSubtablesColor implements BaseColumns {
        public static final String TABLE_NAME_SUBTABLES_COLOR = "subtables_color";
        public static final String COLUMN_NAME_SUBTABLES_COLOR_SET_NUM_STRING = "set_num";
        public static final String COLUMN_NAME_SUBTABLES_COLOR_IDS_BRICK_LINK_INTEGER = "ids_brick_link";
        public static final String COLUMN_NAME_SUBTABLES_COLOR_DESCRS_BRICK_LINK_STRING = "descrs_brick_link";
        public static final String COLUMN_NAME_SUBTABLES_COLOR_IDS_BRICK_OWL_INTEGER = "ids_brick_owl";
        public static final String COLUMN_NAME_SUBTABLES_COLOR_DESCRS_BRICK_OWL_STRING = "descrs_brick_owl";
        public static final String COLUMN_NAME_SUBTABLES_COLOR_IDS_LEGO_INTEGER = "ids_lego";
        public static final String COLUMN_NAME_SUBTABLES_COLOR_DESCRS_LEGO_STRING = "descrs_lego";
        public static final String COLUMN_NAME_SUBTABLES_COLOR_IDS_PEERON_INTEGER = "ids_peeron";
        public static final String COLUMN_NAME_SUBTABLES_COLOR_DESCRS_PEERON_STRING = "descrs_peeron";
        public static final String COLUMN_NAME_SUBTABLES_COLOR_IDS_LDRAW_INTEGER = "ids_ldraw";
        public static final String COLUMN_NAME_SUBTABLES_COLOR_DESCRS_LDRAW_STRING = "descrs_ldraw";


    }

    public static final String SQL_CREATE_TABLE_SUBTABLES_COLOR =
            "CREATE TABLE " + TableEntryPartsSubtablesColor.TABLE_NAME_SUBTABLES_COLOR + " (" +
                    TableEntryPartsSubtablesColor._ID + " INTEGER PRIMARY KEY," +
                    TableEntryPartsSubtablesColor.COLUMN_NAME_SUBTABLES_COLOR_SET_NUM_STRING + " TEXT," +
                    TableEntryPartsSubtablesColor.COLUMN_NAME_SUBTABLES_COLOR_IDS_BRICK_LINK_INTEGER + " INTEGER," +
                    TableEntryPartsSubtablesColor.COLUMN_NAME_SUBTABLES_COLOR_DESCRS_BRICK_LINK_STRING + " TEXT," +
                    TableEntryPartsSubtablesColor.COLUMN_NAME_SUBTABLES_COLOR_IDS_BRICK_OWL_INTEGER + " INTEGER," +
                    TableEntryPartsSubtablesColor.COLUMN_NAME_SUBTABLES_COLOR_DESCRS_BRICK_OWL_STRING + " TEXT," +
                    TableEntryPartsSubtablesColor.COLUMN_NAME_SUBTABLES_COLOR_IDS_LEGO_INTEGER + " INTEGER," +
                    TableEntryPartsSubtablesColor.COLUMN_NAME_SUBTABLES_COLOR_DESCRS_LEGO_STRING + " TEXT," +
                    TableEntryPartsSubtablesColor.COLUMN_NAME_SUBTABLES_COLOR_IDS_PEERON_INTEGER + " INTEGER," +
                    TableEntryPartsSubtablesColor.COLUMN_NAME_SUBTABLES_COLOR_DESCRS_PEERON_STRING + " TEXT," +
                    TableEntryPartsSubtablesColor.COLUMN_NAME_SUBTABLES_COLOR_IDS_LDRAW_INTEGER + " INTEGER," +
                    TableEntryPartsSubtablesColor.COLUMN_NAME_SUBTABLES_COLOR_DESCRS_LDRAW_STRING + " TEXT)";

    public static final String SQL_DELETE_TABLE_SUBTABLES_COLOR =
            "DROP TABLE IF EXISTS " + TableEntryPartsSubtablesColor.TABLE_NAME_SUBTABLES_COLOR;




}
