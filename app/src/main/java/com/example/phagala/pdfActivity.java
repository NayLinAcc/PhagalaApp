package com.example.phagala;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class pdfActivity extends AppCompatActivity {

    private KeyNameMapping keyNameMapping;

    private float startX, startY, endX, endY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pdf);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the KeyNameMapping instance
        keyNameMapping = new KeyNameMapping();

        TextView textView = findViewById(R.id.textView5);

        PDFView pdfView = findViewById(R.id.pdfView);

        Bundle pdfFile = getIntent().getExtras();
        String str = pdfFile.getString("pdfFileName");

        // Get the corresponding name based on the key using the KeyNameMapping instance
        String result = keyNameMapping.getNameForKey(str);

        textView.setText(result);

        pdfView.fromAsset(str)
                .defaultPage(0)
                .enableSwipe(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .onTap(new OnTapListener() {
                    @Override
                    public boolean onTap(MotionEvent e) {
                        if (e.getAction() == MotionEvent.ACTION_DOWN) {
                            startX = e.getX();
                            startY = e.getY();
                        } else if (e.getAction() == MotionEvent.ACTION_UP) {
                            endX = e.getX();
                            endY = e.getY();
                            // Calculate selected text based on startX, startY, endX, endY
                            // Display UI for text selection
                            // Copy selected text to clipboard when required
                        }
                        return true;
                    }

                    public boolean onTap(float x, float y) {
                        // Check if there is a link at the tapped position
                        // If there is, extract the target page and navigate to it
                        // Example:
                        int targetPage = extractTargetPage(x, y);
                        if (targetPage != -1) {
                            pdfView.jumpTo(targetPage);
                            return true; // Consume the tap event
                        }
                        return false; // Allow default tap handling
                    }

                })
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        // Handle page change event
                    }
                })
                .load();
    }
    private int extractTargetPage(float x, float y) {
        // Logic to extract the target page from the tapped position
        // This will depend on how links are represented in your PDF file
        // Return the target page number if a link is found, or -1 if no link is found
        return -1;
    }
}