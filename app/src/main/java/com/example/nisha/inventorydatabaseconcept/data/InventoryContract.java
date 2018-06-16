package com.example.nisha.inventorydatabaseconcept.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class InventoryContract {

    //URI = content://<authority>/path

    public static final String AUTHORITY = "com.example.nisha.inventorydatabaseconcept.provider";
    public static final String PATH = "Inventory";
    public static final String URL = "content://"+ AUTHORITY + "/"+ PATH;

    public static final  int INVENTORY = 100;
    public static final int INVENTORY_ITEM = 200;

    public static Uri CONTENT_URI = Uri.parse(URL);

  /*  //URI matcher
    static {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,PATH,INVENTORY);
        uriMatcher.addURI(AUTHORITY,PATH+"/#",INVENTORY_ITEM);

    }*/



    //private constructor so that no other class can instantiate contract class
    private InventoryContract(){

    }


    //Inner class for each table
    public static final class InventoryEntry implements BaseColumns{
        public static final String TABLE_NAME = "Inventory";

        //Content type
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + TABLE_NAME;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + TABLE_NAME;

        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "ProductName";
        public static final String COLUMN_QUANTITY = "productQuantity";
        public static final String COLUMN_UNIT = "productUnit";
        public static final String COLUMN_BRAND = "productBrand";
        public static final String COLUMN_PRICE = "productPrice";

    }
}
