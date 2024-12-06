package com.example.gambarhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {

    private EditText emailinput;
    private EditText passwordinput;
    private EditText namainput;
    private Button regButton;
    private TextView loginlink;
    private ImageView logingoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        emailinput = findViewById(R.id.daftar_kolom_email);
        namainput = findViewById(R.id.daftar_kolom_nama);
        passwordinput = findViewById(R.id.daftar_kolom_password);
        regButton = findViewById(R.id.daftar_tombol);
        loginlink = findViewById(R.id.targetDaftar);
        logingoogle = findViewById(R.id.logo1);

        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}