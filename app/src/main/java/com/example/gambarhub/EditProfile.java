package com.example.gambarhub;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditProfile extends AppCompatActivity {

    private ImageView profileview;
    private ImageButton tombolkembali;
    private Button tombolsimpan;
    private EditText Editnama, Editusername, Editemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editprofil);

        profileview = findViewById(R.id.editprofil_imageView);
        tombolkembali = findViewById(R.id.editprofil_tombolKembali);
        tombolsimpan = findViewById(R.id.editprofil_tombolSimpanData);

        tombolkembali.setOnClickListener(v -> {
            Intent intent = new Intent(EditProfile.this, Profil.class);
            startActivity(intent);
        });

        tombolsimpan.setOnClickListener(v -> {
//            String nama = Editnama.getText().toString();
//            String username = Editusername.getText().toString();
//            String email = Editemail.getText().toString();

            // Pass data to the Profil activity or save it
            Intent intent = new Intent(EditProfile.this, Profil.class);
//            intent.putExtra("nama", nama);
//            intent.putExtra("username", username);
//            intent.putExtra("email", email);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
