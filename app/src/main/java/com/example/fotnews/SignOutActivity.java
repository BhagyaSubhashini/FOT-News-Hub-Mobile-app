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

public class SignOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_out);

        // Initialize navigation icons and buttons
        ImageView homeIcon = findViewById(R.id.home);
        ImageView settingsIcon = findViewById(R.id.settings);
        Button okButton = findViewById(R.id.btn_ok);
        Button cancelButton = findViewById(R.id.btn_cancel_signout);

        // Set OnClickListener for home icon
        homeIcon.setOnClickListener(v -> {
            Intent intent = new Intent(SignOutActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Finish SignOutActivity to remove it from back stack
        });

        // Set OnClickListener for settings icon
        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(SignOutActivity.this, DeveloperInfoActivity.class);
            startActivity(intent);
            finish(); // Finish SignOutActivity to remove it from back stack
        });

        // Set OnClickListener for OK button
        okButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignOutActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear back stack
            startActivity(intent);
            finish(); // Finish SignOutActivity to remove it from back stack
        });

        // Set OnClickListener for cancel button
        cancelButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignOutActivity.this, UserInfoActivity.class);
            startActivity(intent);
            finish(); // Finish SignOutActivity to remove it from back stack
        });

        // Apply window insets to the root view with ID 'main'
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}