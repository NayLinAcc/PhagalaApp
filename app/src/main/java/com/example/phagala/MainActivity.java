package com.example.phagala;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.CornerFamily;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private SearchViewHelper searchViewHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        createHorizontalScrollViewForLetter('A', R.id.horizontal_scroll_view_a);
        createHorizontalScrollViewForLetter('B', R.id.horizontal_scroll_view_b);
        createHorizontalScrollViewForLetter('C', R.id.horizontal_scroll_view_c);

    }

    private void createHorizontalScrollViewForLetter(char letter, int scrollViewId) {
        HorizontalScrollView horizontalScrollView = findViewById(scrollViewId);
        LinearLayout imageContainer = new LinearLayout(this);

        imageContainer.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        imageContainer.setOrientation(LinearLayout.HORIZONTAL);
        horizontalScrollView.addView(imageContainer);

        // Set click listeners for the view all text views
        TextView viewAllATextView = findViewById(R.id.view_all_a);
        TextView viewAllBTextView = findViewById(R.id.view_all_b);
        TextView viewAllCTextView = findViewById(R.id.view_all_c);

        String tonA = getResources().getString(R.string.tonA);
        String tonB = getResources().getString(R.string.tonB);
        String tonC = getResources().getString(R.string.tonC);

        viewAllATextView.setOnClickListener(v -> openViewAllActivity('A', tonA));

        viewAllBTextView.setOnClickListener(v -> openViewAllActivity('B', tonB));

        viewAllCTextView.setOnClickListener(v -> openViewAllActivity('C', tonC));

        // Get list of PDF files starting with the given letter from assets folder
        List<String> pdfFiles = getPdfFilesStartingWithLetter(letter);

        // Calculate margin in pixels
        int marginInPixels = getResources().getDimensionPixelSize(R.dimen.image_margin);

        // Create image views dynamically for each PDF file
        for (final String pdfFile : pdfFiles) {

            ShapeableImageView imageView = new ShapeableImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            // Create a ShapeAppearanceModel.Builder
            ShapeAppearanceModel.Builder shapeAppearanceModelBuilder = new ShapeAppearanceModel.Builder();

            // Set corner family and size for each corner individually
            shapeAppearanceModelBuilder.setTopLeftCorner(CornerFamily.ROUNDED, getResources().getDimension(R.dimen.image_corner_radius));
            shapeAppearanceModelBuilder.setTopRightCorner(CornerFamily.ROUNDED, getResources().getDimension(R.dimen.image_corner_radius));
            shapeAppearanceModelBuilder.setBottomLeftCorner(CornerFamily.ROUNDED, getResources().getDimension(R.dimen.image_corner_radius));
            shapeAppearanceModelBuilder.setBottomRightCorner(CornerFamily.ROUNDED, getResources().getDimension(R.dimen.image_corner_radius));

            // Build the ShapeAppearanceModel
            ShapeAppearanceModel shapeAppearanceModel = shapeAppearanceModelBuilder.build();

            // Set the ShapeAppearanceModel to the ShapeableImageView
            imageView.setShapeAppearanceModel(shapeAppearanceModel);

//            // Set image resource to corresponding image with same name as PDF file
            String imageName = pdfFile.replace(".pdf", "");
            int imageResource = getResources().getIdentifier(imageName, "drawable", getPackageName());
            if (imageResource != 0) {
                imageView.setImageResource(imageResource);
            }


            if (imageResource != 0) {
                imageView.setImageResource(imageResource);
            }

            // Apply margin to ImageView
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(marginInPixels, 0, 0, 25); // left, top, right, bottom
            imageView.setLayoutParams(params);

            // In the loop where you create image views
            imageView.setOnClickListener(v -> openPdfFile(pdfFile));

            imageContainer.addView(imageView);
        }

    }

    private List<String> getPdfFilesStartingWithLetter(char letter) {
        List<String> pdfFiles = new ArrayList<>();
        try {
            String[] files = getAssets().list("");
            if (files != null) {
                for (String file : files) {
                    if (file.toLowerCase().startsWith(String.valueOf(letter).toLowerCase()) && file.toLowerCase().endsWith(".pdf")) {
                        pdfFiles.add(file);
                    }
                }
            } else {
                // Handle the case where no files are available in assets
                Log.e("getPdfFilesStartingWithLetter", "No files available in assets");
            }
        } catch (IOException e) {
            // Log the exception
            Log.e("getPdfFilesStartingWithLetter", "Error listing assets", e);
        }
        return pdfFiles;
    }


    private void openViewAllActivity(char letter,String ton) {
        Intent intent = new Intent(MainActivity.this, viewAllActivity.class);
        intent.putExtra("letter", letter);
        intent.putExtra("ton", ton);
        startActivity(intent);
    }

    private void openPdfFile(String pdfFileName) {
        // Implement logic to open PDF file (e.g., using an Intent)
        // Example:
        Intent intent = new Intent(MainActivity.this, pdfActivity.class);
        intent.putExtra("pdfFileName", pdfFileName);
        startActivity(intent);
    }
}