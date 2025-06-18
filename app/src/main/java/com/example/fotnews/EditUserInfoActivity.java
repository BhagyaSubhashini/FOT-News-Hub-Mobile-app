package com.example.fotnews;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditUserInfoActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_user_info);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = mAuth.getCurrentUser();

        // Initialize UI elements
        ImageView homeIcon = findViewById(R.id.home);
        ImageView settingsIcon = findViewById(R.id.settings);
        EditText editTextUsername = findViewById(R.id.Username);
        EditText editTextEmail = findViewById(R.id.email);
        Button saveButton = findViewById(R.id.btn_save);
        Button cancelButton = findViewById(R.id.btn_cancel);

        // Load current user data
        if (currentUser != null) {
            String uid = currentUser.getUid();
            db.collection("Users").document(uid).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String username = documentSnapshot.getString("username");
                            String email = documentSnapshot.getString("email");
                            editTextUsername.setText(username != null ? username : "");
                            editTextEmail.setText(email != null ? email : "");
                        }
                    }).addOnFailureListener(e -> {
                        Toast.makeText(this, "Error fetching data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }

        // Set OnClickListener for home icon
        homeIcon.setOnClickListener(v -> {
            Intent intent = new Intent(EditUserInfoActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        // Set OnClickListener for settings icon
        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(EditUserInfoActivity.this, DeveloperInfoActivity.class);
            startActivity(intent);
            finish();
        });

        // Set OnClickListener for save button
        saveButton.setOnClickListener(v -> {
            if (currentUser != null) {
                String newUsername = editTextUsername.getText().toString().trim();
                String newEmail = editTextEmail.getText().toString().trim();

                if (newUsername.isEmpty() || newEmail.isEmpty()) {
                    Toast.makeText(this, "Username and email are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                String uid = currentUser.getUid();
                Map<String, Object> updates = new HashMap<>();
                updates.put("username", newUsername);
                updates.put("email", newEmail);

                db.collection("Users").document(uid).update(updates)
                        .addOnSuccessListener(aVoid -> {
                            if (!newEmail.equals(currentUser.getEmail())) {
                                currentUser.updateEmail(newEmail)
                                        .addOnCompleteListener(task -> {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(EditUserInfoActivity.this, UserInfoActivity.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Toast.makeText(this, "Email update failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            } else {
                                Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EditUserInfoActivity.this, UserInfoActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Update failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });

        // Set OnClickListener for cancel button
        cancelButton.setOnClickListener(v -> {
            Intent intent = new Intent(EditUserInfoActivity.this, UserInfoActivity.class);
            startActivity(intent);
            finish();
        });

        // Apply window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}