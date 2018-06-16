package com.example.nisha.inventorydatabaseconcept.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.nisha.inventorydatabaseconcept.data.InventoryContract.InventoryEntry;

public class InventoryDbHelper extends SQLiteOpenHelper {


    static final String DB_NAME = "inventory.db";
    static final int DB_VERSION = 1;

    public InventoryDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE = "CREATE TABLE "+ InventoryContract.InventoryEntry.TABLE_NAME +
                "( "+ InventoryEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                InventoryEntry.COLUMN_PRODUCT_NAME + " TEXT, "+
                InventoryEntry.COLUMN_QUANTITY + " INTEGER, "+
                InventoryEntry.COLUMN_UNIT + " TEXT, "+
                InventoryEntry.COLUMN_PRICE + " REAL, "+
                InventoryEntry.COLUMN_BRAND + " TEXT);";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
