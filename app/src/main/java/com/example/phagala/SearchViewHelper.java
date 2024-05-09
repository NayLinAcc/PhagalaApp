package com.example.phagala;

import android.content.Context;

import androidx.appcompat.widget.SearchView;

// SearchViewHelper.java
public class SearchViewHelper {

    public interface OnSearchListener {
        void onPdfFileClicked(String pdfFileName);
    }

    private final Context context;
    private final OnSearchListener onSearchListener;

    public SearchViewHelper(Context context, OnSearchListener onSearchListener) {
        this.context = context;
        this.onSearchListener = onSearchListener;
    }

    public void setupSearchView(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle search query change
                filterPdfFiles(newText);
                return true;
            }
        });
    }

    private void filterPdfFiles(String query) {
        // Implement filtering logic here
        // This method should notify the MainActivity or call a method in MainActivity
        // to update the UI based on the filtered PDF files
    }

    // Method to open the corresponding PDF file
    private void openPdfFile(String pdfFileName) {
        if (onSearchListener != null) {
            onSearchListener.onPdfFileClicked(pdfFileName);
        }
    }
}
