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

public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_info);

        // Initialize navigation icons
        ImageView homeIcon = findViewById(R.id.home);
        ImageView settingsIcon = findViewById(R.id.settings);
        Button editButton = findViewById(R.id.btn_edit);
        Button signoutButton = findViewById(R.id.btn_sign_out);
        Button deleteButton = findViewById(R.id.btn_delete);

        // Set OnClickListener for home icon
        homeIcon.setOnClickListener(v -> {
            Intent intent = new Intent(UserInfoActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Finish UserInfoActivity to remove it from back stack
        });

        // Set OnClickListener for settings icon
        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(UserInfoActivity.this, DeveloperInfoActivity.class);
            startActivity(intent);
            finish(); // Finish UserInfoActivity to remove it from back stack
        });

        // Set OnClickListener for edit button
        editButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserInfoActivity.this, EditUserInfoActivity.class);
            startActivity(intent);
            finish(); // Finish UserInfoActivity to remove it from back stack
        });

        // Set OnClickListener for signout button
        signoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserInfoActivity.this, SignOutActivity.class);
            startActivity(intent);
            finish(); // Finish UserInfoActivity to remove it from back stack
        });

        // Set OnClickListener for delete button
        deleteButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserInfoActivity.this, DeleteAccountActivity.class);
            startActivity(intent);
            finish(); // Finish UserInfoActivity to remove it from back stack
        });

        // Apply window insets to the root view with ID 'main'
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}