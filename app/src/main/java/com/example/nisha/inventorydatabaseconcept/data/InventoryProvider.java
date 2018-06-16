package com.example.nisha.inventorydatabaseconcept.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class InventoryProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private InventoryDbHelper dbHelper;

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        String authority = InventoryContract.AUTHORITY;

        uriMatcher.addURI(authority, InventoryContract.InventoryEntry.TABLE_NAME, InventoryContract.INVENTORY);

        uriMatcher.addURI(authority, InventoryContract.InventoryEntry.TABLE_NAME+"/#", InventoryContract.INVENTORY_ITEM);

        return uriMatcher;
    }


    @Override
    public boolean onCreate() {
        dbHelper = new InventoryDbHelper(getContext());
    return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selArgs, @Nullable String sort) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();


        switch (sUriMatcher.match(uri)){
            case InventoryContract.INVENTORY:
                queryBuilder.setTables(InventoryContract.InventoryEntry.TABLE_NAME);
                break;

             case  InventoryContract.INVENTORY_ITEM:
                 queryBuilder.setTables(InventoryContract.InventoryEntry.TABLE_NAME);
                 queryBuilder.appendWhere( InventoryContract.InventoryEntry.COLUMN_ID + "=" + uri.getLastPathSegment());
                 break;
            default:

                throw new IllegalArgumentException("Unknown URI " + uri);

        }
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = queryBuilder.query(db,projection,selection,selArgs,null,null,sort);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
       switch (sUriMatcher.match(uri)){
           case InventoryContract.INVENTORY:
               return InventoryContract.InventoryEntry.CONTENT_TYPE;
            case InventoryContract.INVENTORY_ITEM:
                return InventoryContract.InventoryEntry.CONTENT_ITEM_TYPE;
           default:
               throw new UnsupportedOperationException("Unknown uri: " + uri);
       }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        //Get writable database

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // insert content values in database
        long rowId = database.insert(InventoryContract.InventoryEntry.TABLE_NAME,null,contentValues);
        if(rowId>0) {
            Uri returnUri = ContentUris.withAppendedId(InventoryContract.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(returnUri,null);
            return returnUri;
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String whereClause, @Nullable String[] whereArgs) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        int rows = database.delete(InventoryContract.InventoryEntry.TABLE_NAME,whereClause,whereArgs);
        if (rows != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
