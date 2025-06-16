package com.example.fotnews;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Initialize the Login button
        Button loginButton = findViewById(R.id.Loginbtn);

        // Set OnClickListener to navigate to HomeActivity with error handling
        loginButton.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Closes LoginActivity after navigation
            } catch (Exception e) {
                Toast.makeText(this, "Error navigating to Home: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Signup TextView
        TextView signupTextView = findViewById(R.id.SignupTextView);

        // Set OnClickListener to navigate to RegisterActivity
        signupTextView.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}