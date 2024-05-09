package com.example.phagala;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class viewAllActivity extends AppCompatActivity {

    // Instantiate the KeyNameMapping class
    private KeyNameMapping keyNameMapping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_all);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView backImage = findViewById(R.id.backImage);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Load and display the PDF file when button is clicked
                finish();
            }
        });

        // Initialize the KeyNameMapping instance
        keyNameMapping = new KeyNameMapping();

        Bundle letter = getIntent().getExtras();
        char str = letter.getChar("letter");
        String ton = letter.getString("ton");

        // Get list of PDF files starting with the given letter from assets folder
        List<String> pdfFiles = getPdfFilesStartingWithLetter(str);

        // Get the LinearLayout from the layout resource
        LinearLayout linearLayout = findViewById(R.id.linear_layout_buttons);
        TextView textView5 = findViewById(R.id.textView5);
        textView5.setText(ton);

        // Create buttons dynamically for each PDF file
        for (final String pdfFile : pdfFiles) {

            // Create a new ConstraintLayout
            ConstraintLayout mainConstraintLayout = new ConstraintLayout(this);
            mainConstraintLayout.setId(View.generateViewId());

            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,335);

            // Set margins (in pixels)
            int marginInPixels = (int) (20 * getResources().getDisplayMetrics().density); // For example, 16dp
            params.setMargins(20, marginInPixels, 20, 0);

            mainConstraintLayout.setLayoutParams(params);


            // Create a new ConstraintLayout
            ConstraintLayout constraintLayout = new ConstraintLayout(this);
            constraintLayout.setBackgroundResource(R.drawable.buttom_corner_radius);// Set background color
            constraintLayout.setId(View.generateViewId());
            constraintLayout.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    280)); // Set the height

            TextView nameText = new TextView(this);
            nameText.setId(View.generateViewId());
            nameText.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            nameText.setTextSize(17);

            TextView personText = new TextView(this);
            personText.setId(View.generateViewId());
            personText.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            personText.setTextSize(13);
            personText.setTextColor(getResources().getColor(R.color.gray));
            personText.setText("ဘဒ္ဒန္တဇာဂရဗုဒ္ဓိ");

            ShapeableImageView downloadImage = new ShapeableImageView(this);
            downloadImage.setId(View.generateViewId());
            downloadImage.setImageResource(R.drawable.download);

            // Create a new ShapeableImageView
            ShapeableImageView imageView = new ShapeableImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 300));
            imageView.setId(View.generateViewId()); // Set a unique ID for the ImageView

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

            // Add the imageView to the mainConstraintLayout
            mainConstraintLayout.addView(imageView);

            // Add the constraintLayout to the mainConstraintLayout
            mainConstraintLayout.addView(constraintLayout);
            constraintLayout.addView(nameText);
            constraintLayout.addView(personText);
            constraintLayout.addView(downloadImage);

            // Apply ConstraintSet to center imageView in mainConstraintLayout
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);

            constraintSet.connect(nameText.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START, 320);
            constraintSet.connect(nameText.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 15);

            constraintSet.connect(personText.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START, 320);
            constraintSet.connect(personText.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 90);

            constraintSet.connect(downloadImage.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END, 20);
            constraintSet.connect(downloadImage.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 75);


            constraintSet.applyTo(constraintLayout);


            // Apply ConstraintSet to center imageView in mainConstraintLayout
            ConstraintSet mainConstraintSet = new ConstraintSet();
            mainConstraintSet.clone(mainConstraintLayout);

            mainConstraintSet.connect(constraintLayout.getId(), ConstraintSet.BOTTOM, mainConstraintLayout.getId(), ConstraintSet.BOTTOM, 0);

            // Apply constraints to imageView
            mainConstraintSet.connect(imageView.getId(), ConstraintSet.TOP, mainConstraintLayout.getId(), ConstraintSet.TOP, 0);
            mainConstraintSet.connect(imageView.getId(), ConstraintSet.START, mainConstraintLayout.getId(), ConstraintSet.START, 53);
            //            constraintSet.centerHorizontally(imageView.getId(), ConstraintSet.PARENT_ID);

            // Apply the constraints to the mainConstraintLayout
            mainConstraintSet.applyTo(mainConstraintLayout);

            imageView.bringToFront();

            // Add the mainConstraintLayout to the LinearLayout
            linearLayout.addView(mainConstraintLayout);

            // Get the corresponding name based on the key using the KeyNameMapping instance
            String result = keyNameMapping.getNameForKey(pdfFile);

            // Set the button text
            nameText.setText(result);

            // Set image resource to corresponding image with same name as PDF file
            String imageName = pdfFile.replace(".pdf", "");
            int imageResource = getResources().getIdentifier(imageName, "drawable", getPackageName());
            if (imageResource != 0) {
                imageView.setImageResource(imageResource);
            }

            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Load and display the PDF file when button is clicked
                    loadPdfFromAssets(pdfFile);
                }
            });

            downloadImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
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

    private void loadPdfFromAssets(String pdfFileName) {
        // Implement logic to load and display the PDF file
        // For example:
        Intent intent = new Intent(viewAllActivity.this, pdfActivity.class);
        intent.putExtra("pdfFileName", pdfFileName);
        startActivity(intent);
    }


}