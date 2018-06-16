package com.example.nisha.inventorydatabaseconcept.activities;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.nisha.inventorydatabaseconcept.R;
import com.example.nisha.inventorydatabaseconcept.data.InventoryContract;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void save(View view) {

        EditText name,price,quantity,unit,brand;

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        unit = findViewById(R.id.unit);
        quantity = findViewById(R.id.quantity);
        brand = findViewById(R.id.brand);


        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME,name.getText().toString());
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_PRICE,Double.parseDouble(price.getText().toString()));
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_QUANTITY,Integer.parseInt(quantity.getText().toString()));
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_UNIT,unit.getText().toString());
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_BRAND,brand.getText().toString());

        getContentResolver().insert(InventoryContract.CONTENT_URI,contentValues);
        finish();
    }
}
