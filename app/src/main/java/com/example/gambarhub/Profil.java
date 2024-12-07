package com.example.gambarhub;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Profil extends AppCompatActivity {

    private ImageView profileview;
    private TextView emailview;
    private TextView namaview;
    private TextView usernameview;
    private Button tomboledit;

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

        tomboledit.setOnClickListener(v -> {
            Intent intent2 = new Intent(Profil.this, EditProfile.class);
            startActivity(intent2);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}