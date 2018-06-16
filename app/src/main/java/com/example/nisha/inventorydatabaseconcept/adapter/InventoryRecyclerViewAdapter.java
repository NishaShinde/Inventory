package com.example.nisha.inventorydatabaseconcept.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nisha.inventorydatabaseconcept.Inventory;
import com.example.nisha.inventorydatabaseconcept.R;

import java.util.ArrayList;

public class InventoryRecyclerViewAdapter  extends
        RecyclerView.Adapter<InventoryRecyclerViewAdapter.ViewHolder> {

    ArrayList<Inventory> inventoryArrayList;

    public InventoryRecyclerViewAdapter(ArrayList<Inventory> inventoryList){
       inventoryArrayList = inventoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.row_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

         Inventory inventory = inventoryArrayList.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(inventory.getProduct_name());
        TextView priceTextView = viewHolder.priceTextView;
        priceTextView.setText(String.valueOf(inventory.getId()));

    }
    @Override
    public int getItemCount() {
        return inventoryArrayList.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView priceTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.product_name);
            priceTextView = (TextView) itemView.findViewById(R.id.product_price);

        }
    }
}