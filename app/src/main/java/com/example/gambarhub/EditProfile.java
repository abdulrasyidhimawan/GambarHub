package com.example.gambarhub;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class EditProfile extends AppCompatActivity {

    private ImageButton tombolkembali;
    private Button tombolsimpan, tomboleditpp;
    private EditText editnama, editemail, editusername;
    private ImageView profilePhoto;
    // BORDER
    private boolean isImage1 = true;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private Bitmap profileBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editprofil);

        profilePhoto = findViewById(R.id.editprofil_imageView);
        tombolkembali = findViewById(R.id.editprofil_tombolKembali);
        tombolsimpan = findViewById(R.id.editprofil_tombolSimpanData);
        tomboleditpp = findViewById(R.id.editprofil_tombolEditGambar);
        editnama = findViewById(R.id.editprofil_kolomEditNama);
        editemail = findViewById(R.id.editprofil_kolomEditEmail);
        editusername = findViewById(R.id.editprofil_kolomEditUsername);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //BORDER

        tombolkembali.setOnClickListener(v -> {
            Intent intent = new Intent(EditProfile.this, Profil.class);
            startActivity(intent);
        });

        tomboleditpp.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                openImageChooser();
            }
        });

        tombolsimpan.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                saveProfile();
            }
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                profileBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profilePhoto.setImageBitmap(profileBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveProfile() {
        String name = editnama.getText().toString().trim();
        String username = editusername.getText().toString().trim();
        String email = editemail.getText().toString().trim();

        Intent intent = new Intent(EditProfile.this, Profil.class);
        intent.putExtra("name", name);
        intent.putExtra("username", username);
        intent.putExtra("email", email);

        if (profileBitmap != null) {
            intent.putExtra("profilePhoto", imageUri.toString());
        }

        startActivity(intent);
        Toast.makeText(this, "Profile saved", Toast.LENGTH_SHORT).show();
    }
}
