package com.example.fotnews;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserInfoActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_info);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Initialize UI elements
        ImageView homeIcon = findViewById(R.id.home);
        ImageView settingsIcon = findViewById(R.id.settings);
        Button editButton = findViewById(R.id.btn_edit);
        Button signoutButton = findViewById(R.id.btn_sign_out);
        Button deleteButton = findViewById(R.id.btn_delete);
        TextView textViewUsername = findViewById(R.id.name);
        TextView textViewEmail = findViewById(R.id.userEmail);

        // Load current user data
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            db.collection("Users").document(uid).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String username = documentSnapshot.getString("username");
                            String email = documentSnapshot.getString("email");
                            textViewUsername.setText(username != null ? username : "N/A");
                            textViewEmail.setText(email != null ? email : "N/A");
                        } else {
                            Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Error fetching data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show();
        }

        // Set OnClickListener for home icon
        homeIcon.setOnClickListener(v -> {
            Intent intent = new Intent(UserInfoActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        // Set OnClickListener for settings icon
        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(UserInfoActivity.this, DeveloperInfoActivity.class);
            startActivity(intent);
            finish();
        });

        // Set OnClickListener for edit button
        editButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserInfoActivity.this, EditUserInfoActivity.class);
            startActivity(intent);
            finish();
        });

        // Set OnClickListener for signout button
        signoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserInfoActivity.this, SignOutActivity.class);
            startActivity(intent);
            finish();
        });

        // Set OnClickListener for delete button
        deleteButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserInfoActivity.this, DeleteAccountActivity.class);
            startActivity(intent);
            finish();
        });

        // Apply window insets to the root view with ID 'main'
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}