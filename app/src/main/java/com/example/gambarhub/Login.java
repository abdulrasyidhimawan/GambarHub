package com.example.gambarhub;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Login extends AppCompatActivity {
    private EditText emailinput;
    private EditText passwordinput;
    private Button loginButton;
    private TextView daftarlink;
    private ImageView logingoogle;
    private FirebaseAuth firebaseAuth;


//    private final String validEmail = "Gambarhub";
//    private final String validPassword = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        emailinput = findViewById(R.id.masuk_kolom_email);
        passwordinput = findViewById(R.id.masuk_kolom_password);
        loginButton = findViewById(R.id.masuk_tombol);
        daftarlink = findViewById(R.id.targetDaftar);
        logingoogle = findViewById(R.id.logo1);

        firebaseAuth = FirebaseAuth.getInstance();

        daftarlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    private void loginUser() {
        String mail = emailinput.getText().toString();
        String pass = passwordinput.getText().toString();

        if (TextUtils.isEmpty(mail)){
            emailinput.setError("Email tidak boleh kosong!");
            return;
        }
        if (TextUtils.isEmpty(pass)){
            passwordinput.setError("Password tidak boleh kosong!");
            return;
        }
        if (pass.length() < 6){
            passwordinput.setError("Password harus lebih dari 6 karakter!");
        }
        firebaseAuth.signInWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, Beranda.class)); // Ganti ke activity utama
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Login gagal: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}