package com.example.domashnee_zadanie_9.Services;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.domashnee_zadanie_9.DBContent.ShoppingDatabaseContract;

import java.util.Objects;

public class ShoppingContentProvider extends ContentProvider {
    private ShoppingDatabaseHelper dbHelper;

    public static final String AUTHORITY = "com.example.shoppingapp.provider";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, "Lists", 1);
        uriMatcher.addURI(AUTHORITY, "Type", 2);
        uriMatcher.addURI(AUTHORITY, "Product", 3);
    }
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // URIs
    public static final Uri LISTS_URI = Uri.withAppendedPath(BASE_CONTENT_URI, "Lists");
    public static final Uri TYPE_URI = Uri.withAppendedPath(BASE_CONTENT_URI, "Type");
    public static final Uri PRODUCT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, "Product");
    //private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    @Override
    public boolean onCreate() {
        dbHelper = new ShoppingDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;


        switch (uriMatcher.match(uri)) {
            case 1:
                cursor = db.query(ShoppingDatabaseContract.ListsTable.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case 2:
                cursor = db.query(ShoppingDatabaseContract.TypeTable.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case 3:
                cursor = db.query(ShoppingDatabaseContract.ProductTable.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        cursor.setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id;

        switch (uriMatcher.match(uri)) {
            case 1:
                id = db.insert(ShoppingDatabaseContract.ListsTable.TABLE_NAME, null, values);
                break;
            case 2:
                id = db.insert(ShoppingDatabaseContract.TypeTable.TABLE_NAME, null, values);
                break;
            case 3:
                id = db.insert(ShoppingDatabaseContract.ProductTable.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsDeleted;

        switch (uriMatcher.match(uri)) {
            case 1:
                rowsDeleted = db.delete(ShoppingDatabaseContract.ListsTable.TABLE_NAME, selection, selectionArgs);
                break;
            case 2:
                rowsDeleted = db.delete(ShoppingDatabaseContract.TypeTable.TABLE_NAME, selection, selectionArgs);
                break;
            case 3:
                rowsDeleted = db.delete(ShoppingDatabaseContract.ProductTable.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsUpdated;

        switch (uriMatcher.match(uri)) {
            case 1:
                rowsUpdated = db.update(ShoppingDatabaseContract.ListsTable.TABLE_NAME, values, selection, selectionArgs);
                break;
            case 2:
                rowsUpdated = db.update(ShoppingDatabaseContract.TypeTable.TABLE_NAME, values, selection, selectionArgs);
                break;
            case 3:
                rowsUpdated = db.update(ShoppingDatabaseContract.ProductTable.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
