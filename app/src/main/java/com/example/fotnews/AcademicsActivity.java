package com.example.fotnews;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.ImageView;

public class AcademicsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_academics);

        // Initialize navigation icons
        ImageView sportsIcon = findViewById(R.id.sportsicon);
        ImageView eventsIcon = findViewById(R.id.eventsicon);
        ImageView accountIcon = findViewById(R.id.account);
        ImageView settingsIcon = findViewById(R.id.settings);

        // Set OnClickListener for sportsicon (navigate to HomeActivity)
        sportsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(AcademicsActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        // Set OnClickListener for eventsicon
        eventsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(AcademicsActivity.this, EventsActivity.class);
            startActivity(intent);
        });

        // Set OnClickListener for account icon
        accountIcon.setOnClickListener(v -> {
            Intent intent = new Intent(AcademicsActivity.this, UserInfoActivity.class);
            startActivity(intent);
            finish(); // Finish AcademicsActivity to remove it from back stack
        });

        // Set OnClickListener for settings icon
        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(AcademicsActivity.this, DeveloperInfoActivity.class);
            startActivity(intent);
            finish(); // Finish AcademicsActivity to remove it from back stack
        });

        // Apply window insets to the root view with ID 'main'
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}