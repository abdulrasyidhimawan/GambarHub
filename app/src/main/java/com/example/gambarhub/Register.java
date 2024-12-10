package com.example.gambarhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private EditText emailinput;
    private EditText passwordinput;
    private EditText namainput;
    private Button regButton;
    private TextView loginlink;
    private ImageView logingoogle;

    private FirebaseAuth firebaseAuth;

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

        firebaseAuth = FirebaseAuth.getInstance();

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

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String uname = namainput.getText().toString();
        String mail = emailinput.getText().toString();
        String pass = passwordinput.getText().toString();

        if (uname.isEmpty()) {
            namainput.setError("Nama tidak boleh kosong!");
            return;
        }

        if (mail.isEmpty()) {
            emailinput.setError("Email tidak boleh kosong!");
            return;
        }

        if (pass.isEmpty()) {
            passwordinput.setError("Password tidak boleh kosong!");
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(Register.this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, Login.class));
                    finish();
                }
            } else {
                Toast.makeText(Register.this, "Registrasi gagal: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}