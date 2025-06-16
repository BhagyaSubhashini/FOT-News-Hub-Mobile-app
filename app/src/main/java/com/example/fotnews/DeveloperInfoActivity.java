package com.example.fotnews;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.ImageView;
import android.widget.Button;

public class DeveloperInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_developer_info);

        // Initialize navigation icons
        ImageView homeIcon = findViewById(R.id.home);
        ImageView settingsIcon = findViewById(R.id.settings);
        Button exitButton = findViewById(R.id.btn_exit);

        // Set OnClickListener for home icon
        homeIcon.setOnClickListener(v -> {
            Intent intent = new Intent(DeveloperInfoActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Finish DeveloperInfoActivity to remove it from back stack
        });

        // Set OnClickListener for settings icon
        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(DeveloperInfoActivity.this, DeveloperInfoActivity.class);
            startActivity(intent);
            finish(); // Finish DeveloperInfoActivity to remove it from back stack (no change in this case)
        });

        // Apply window insets to the root view with ID 'main'
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set OnClickListener for exit button
        exitButton.setOnClickListener(v -> {
            Intent intent = new Intent(DeveloperInfoActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Finish DeveloperInfoActivity to remove it from back stack
        });
    }
}