package com.example.fotnews;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Initialize navigation icons
        ImageView academicsIcon = findViewById(R.id.academicsicon);
        ImageView eventsIcon = findViewById(R.id.eventsicon);

        // Set OnClickListener for academicsicon
        academicsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AcademicsActivity.class);
            startActivity(intent);
        });

        // Set OnClickListener for eventsicon
        eventsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, EventsActivity.class);
            startActivity(intent);
        });

        // Apply window insets to the root view with ID 'main'
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}