package com.example.gambarhub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class Profil extends AppCompatActivity {

    private ImageView profileview;
    private TextView emailview;
    private TextView namaview;
    private TextView usernameview;
    private Button tomboledit;
    private Button tombolkeluar;
    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profil);

        profileview = findViewById(R.id.profil_imageView);
        emailview = findViewById(R.id.profil_labelViewEmail);
        namaview = findViewById(R.id.profil_labelViewNama);
        usernameview = findViewById(R.id.profil_labelViewUsername);
        tomboledit = findViewById(R.id.profil_tombolEdit);
        tombolkeluar = findViewById(R.id.profil_tombolKeluar);
        firebaseAuth = FirebaseAuth.getInstance();

        // Configure Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tomboledit.setOnClickListener(v -> {
            Intent intent = new Intent(Profil.this, EditProfile.class);
            startActivity(intent);
        });

        tombolkeluar.setOnClickListener(v -> {
            logoutUser();
        });

        if (getIntent().hasExtra("name")) {
            Intent intent = getIntent();
            String name = intent.getStringExtra("name");
            String username = intent.getStringExtra("username");
            String email = intent.getStringExtra("email");
            String profilePhotoUri = intent.getStringExtra("profilePhoto");
            if (profilePhotoUri != null) {
                profileview.setImageURI(Uri.parse(profilePhotoUri));
            } else {
                profileview.setImageResource(R.drawable.defaultpp);
            }
            namaview.setText(name);
            usernameview.setText(username);
            emailview.setText(email);
        } else {
            namaview.setText("Nama Lengkap mu");
            usernameview.setText("Username");
            emailview.setText("Email@gmail.com");
            profileview.setImageResource(R.drawable.defaultpp);
        }
    }

    private void logoutUser() {
        // Sign out from Firebase
        firebaseAuth.signOut();

        // Sign out from Google
        mGoogleSignInClient.signOut().addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                // Redirect to login screen
                startActivity(new Intent(Profil.this, Login.class));
                finish();
            } else {
                Toast.makeText(Profil.this, "Logout failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}