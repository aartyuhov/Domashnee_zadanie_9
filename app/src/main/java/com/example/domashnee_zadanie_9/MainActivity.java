package com.example.domashnee_zadanie_9;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.domashnee_zadanie_9.DBContent.ShoppingDatabaseContract;
import com.example.domashnee_zadanie_9.Services.ShoppingContentProvider;

public class MainActivity extends AppCompatActivity {

    Button initializeButton;
     TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initializeButton = findViewById(R.id.initialize_button);
        resultTextView = findViewById(R.id.resultTextView);

        initializeButton.setOnClickListener(v -> {
            initializeDatabase();
            showResults();
        });
    }

    private void initializeDatabase() {
        // Example: Insert a list
        ContentValues listValues = new ContentValues();
        listValues.put(ShoppingDatabaseContract.ListsTable.COLUMN_NAME, "List 1");
        listValues.put(ShoppingDatabaseContract.ListsTable.COLUMN_DATE, System.currentTimeMillis());
        listValues.put(ShoppingDatabaseContract.ListsTable.COLUMN_DESCRIPTION, "Grocery shopping");
        getContentResolver().insert(ShoppingContentProvider.LISTS_URI, listValues);

        // Example: Insert a type
        ContentValues typeValues = new ContentValues();
        typeValues.put(ShoppingDatabaseContract.TypeTable.COLUMN_LABEL, "kg");
        typeValues.put(ShoppingDatabaseContract.TypeTable.COLUMN_RULE, "float");
        getContentResolver().insert(ShoppingContentProvider.TYPE_URI, typeValues);

        // Example: Insert a product
        ContentValues productValues = new ContentValues();
        productValues.put(ShoppingDatabaseContract.ProductTable.COLUMN_NAME, "Milk");
        productValues.put(ShoppingDatabaseContract.ProductTable.COLUMN_COUNT, 2.0);
        productValues.put(ShoppingDatabaseContract.ProductTable.COLUMN_LIST_ID, 1);
        productValues.put(ShoppingDatabaseContract.ProductTable.COLUMN_CHECKED, 0);
        productValues.put(ShoppingDatabaseContract.ProductTable.COLUMN_COUNT_TYPE, 2);
        getContentResolver().insert(ShoppingContentProvider.PRODUCT_URI, productValues);

        Toast.makeText(this, "Database initialized!", Toast.LENGTH_SHORT).show();

    }


    //Example of show result Table Lists - description
    @SuppressLint("Range")
    private void showResults() {
        ContentResolver resolver = getContentResolver();
        Uri uri = ShoppingContentProvider.BASE_CONTENT_URI.buildUpon().appendPath("Lists").build();

        Cursor cursor = resolver.query(uri, null, null, null, null);

        StringBuilder resultBuilder = new StringBuilder("Products:\n");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                resultBuilder.append(cursor.getString(cursor.getColumnIndex("description")))
                        .append("\n");
            }
            resultTextView.setText(resultBuilder.toString());
            cursor.close();
        }
    }
}