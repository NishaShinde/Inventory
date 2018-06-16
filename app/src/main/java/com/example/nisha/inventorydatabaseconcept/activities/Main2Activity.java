package com.example.nisha.inventorydatabaseconcept.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.nisha.inventorydatabaseconcept.Inventory;
import com.example.nisha.inventorydatabaseconcept.R;
import com.example.nisha.inventorydatabaseconcept.SettingsActivity;
import com.example.nisha.inventorydatabaseconcept.adapter.InventoryRecyclerViewAdapter;
import com.example.nisha.inventorydatabaseconcept.data.InventoryContract;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final Uri CONTENT_URI = InventoryContract.CONTENT_URI;

    ArrayList<Inventory> inventoryArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private InventoryRecyclerViewAdapter recyclerViewAdapter;

    private int oldArraySize;
    private int newArraySize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        recyclerView = findViewById(R.id.rvInventory);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

      //  recyclerViewAdapter = new InventoryRecyclerViewAdapter(inventoryArrayList);

        oldArraySize = inventoryArrayList.size();

        recyclerView.setAdapter(recyclerViewAdapter);
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                deleteItem(viewHolder.getAdapterPosition());
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                // view the background view
            }
        };
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        getSupportLoaderManager().initLoader(100, null, this);

    }

    private void deleteItem(int adapterPosition) {
        int id = inventoryArrayList.get(adapterPosition).getId();
        getContentResolver().delete(CONTENT_URI, InventoryContract.InventoryEntry.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        recyclerViewAdapter.notifyItemRemoved(adapterPosition);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(this, CONTENT_URI, null, null, null, InventoryContract.InventoryEntry.COLUMN_ID);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();

        InventoryRecyclerViewAdapter adapter = new InventoryRecyclerViewAdapter(data);
        recyclerView.setAdapter(adapter);

       /* if (data != null) {
            inventoryArrayList.clear();
            while (data.moveToNext()) {

                int columnIndexProductName = data.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME);
                int columnIndexProductPrice = data.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRICE);
                int columnIndexProductQuantity = data.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_QUANTITY);
                int columnIndexProductUnit = data.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_UNIT);
                int columnIndexProductBrand = data.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_BRAND);
                int columnIndexProductId = data.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_ID);


                String prodcutName = data.getString(columnIndexProductName);
                String productUnit = data.getString(columnIndexProductUnit);
                double productPrice = data.getDouble(columnIndexProductPrice);
                int productQuantitiy = data.getInt(columnIndexProductQuantity);
                String productBrand = data.getString(columnIndexProductBrand);
                int id = data.getInt(columnIndexProductId);

                Inventory inventory = new Inventory(prodcutName, productQuantitiy, productPrice, productUnit, productBrand, id);
                inventoryArrayList.add(inventory);

            }

            newArraySize = inventoryArrayList.size();


        } else {
            Toast.makeText(Main2Activity.this, "Data not avaialble", Toast.LENGTH_SHORT).show();
        }

        if(oldArraySize + 1 == newArraySize) {
            // Meaning - only one element added
            recyclerViewAdapter.notifyItemInserted(newArraySize);
        } else if (newArraySize != 0){
            recyclerViewAdapter.notifyDataSetChanged();
            oldArraySize = newArraySize;
        } else {
            recyclerViewAdapter.notifyDataSetChanged();
        }*/

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(Main2Activity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
