package com.example.fotnews;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.ImageView;

public class EditUserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_user_info);

        // Initialize navigation icons and button
        ImageView homeIcon = findViewById(R.id.home);
        ImageView settingsIcon = findViewById(R.id.settings);
        Button cancelButton = findViewById(R.id.btn_cancel);

        // Set OnClickListener for home icon
        homeIcon.setOnClickListener(v -> {
            Intent intent = new Intent(EditUserInfoActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Finish EditUserInfoActivity to remove it from back stack
        });

        // Set OnClickListener for settings icon
        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(EditUserInfoActivity.this, DeveloperInfoActivity.class);
            startActivity(intent);
            finish(); // Finish EditUserInfoActivity to remove it from back stack
        });

        // Set OnClickListener for cancel button
        cancelButton.setOnClickListener(v -> {
            Intent intent = new Intent(EditUserInfoActivity.this, UserInfoActivity.class);
            startActivity(intent);
            finish(); // Finish EditUserInfoActivity to remove it from back stack
        });

        // Apply window insets to the root view with ID 'main'
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}