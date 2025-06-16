package com.example.fotnews;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.ImageView;

public class EventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_events);

        // Initialize navigation icons
        ImageView sportsIcon = findViewById(R.id.sportsicon);
        ImageView academicsIcon = findViewById(R.id.academicsicon);
        ImageView accountIcon = findViewById(R.id.account);
        ImageView settingsIcon = findViewById(R.id.settings);

        // Set OnClickListener for sportsicon (navigate to HomeActivity)
        sportsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(EventsActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        // Set OnClickListener for academicsicon
        academicsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(EventsActivity.this, AcademicsActivity.class);
            startActivity(intent);
        });

        // Set OnClickListener for account icon
        accountIcon.setOnClickListener(v -> {
            Intent intent = new Intent(EventsActivity.this, UserInfoActivity.class);
            startActivity(intent);
            finish(); // Finish EventsActivity to remove it from back stack
        });

        // Set OnClickListener for settings icon
        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(EventsActivity.this, DeveloperInfoActivity.class);
            startActivity(intent);
            finish(); // Finish EventsActivity to remove it from back stack
        });

        // Apply window insets to the root view with ID 'main'
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}