package com.example.domashnee_zadanie_9.Services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.domashnee_zadanie_9.DBContent.ShoppingDatabaseContract;

public class ShoppingDatabaseHelper extends SQLiteOpenHelper {
    public ShoppingDatabaseHelper(Context context) {
        super(context, ShoppingDatabaseContract.DATABASE_NAME, null, ShoppingDatabaseContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ShoppingDatabaseContract.ListsTable.CREATE_TABLE);
        db.execSQL(ShoppingDatabaseContract.TypeTable.CREATE_TABLE);
        db.execSQL(ShoppingDatabaseContract.ProductTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ShoppingDatabaseContract.ListsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ShoppingDatabaseContract.TypeTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ShoppingDatabaseContract.ProductTable.TABLE_NAME);
        onCreate(db);
    }
}
