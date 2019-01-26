package com.example.ronzi.test;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

public class SearchArrayAdapter extends ArrayAdapter<String> {
    public SearchArrayAdapter(Context context, int resource, String[] strings) {
        super(context, resource);
        stringsInArray=strings;
        addAll(stringsInArray);
        notifyDataSetChanged();
    }
    private String[] stringsInArray;

    private Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if(constraint != null) {
                String stringConstraint=constraint.toString().toLowerCase();
                List<String> filteredProducts = new ArrayList<String>();
                for(String currentGenericProduct : stringsInArray){
                    if(currentGenericProduct.toLowerCase().contains(stringConstraint)){
                        filteredProducts.add(currentGenericProduct);
                    }
                }
                // Now assign the values and count to the FilterResults object
                filterResults.values = filteredProducts;
                filterResults.count = filteredProducts.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence contraint, FilterResults results) {
            clear();
            if(results != null && results.count > 0) {
                addAll((ArrayList)results.values);
                notifyDataSetChanged();
            }
            else {
                notifyDataSetInvalidated();
            }
        }
    };

    @Override
        public Filter getFilter() {
            return myFilter;
        }
    };
