package com.example.domashnee_zadanie_9.DBContent;

public final class ShoppingDatabaseContract  {

    private ShoppingDatabaseContract() {}
    public static final String DATABASE_NAME = "shopping.db";
    public static final int DATABASE_VERSION = 1;

    public static final class ListsTable {
        public static final String TABLE_NAME = "Lists";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_DESCRIPTION = "description";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT NOT NULL, "
                + COLUMN_DATE + " INTEGER NOT NULL, "
                + COLUMN_DESCRIPTION + " TEXT);";
    }

    public static final class TypeTable {
        public static final String TABLE_NAME = "Type";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_LABEL = "label";
        public static final String COLUMN_RULE = "rule";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_LABEL + " TEXT NOT NULL, "
                + COLUMN_RULE + " TEXT NOT NULL);";
    }

    public static final class ProductTable {
        public static final String TABLE_NAME = "Product";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_COUNT = "count";
        public static final String COLUMN_LIST_ID = "list_id";
        public static final String COLUMN_CHECKED = "checked";
        public static final String COLUMN_COUNT_TYPE = "count_type";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT NOT NULL, "
                + COLUMN_COUNT + " REAL NOT NULL, "
                + COLUMN_LIST_ID + " INTEGER NOT NULL, "
                + COLUMN_CHECKED + " INTEGER NOT NULL, "
                + COLUMN_COUNT_TYPE + " INTEGER NOT NULL);";
    }
}
