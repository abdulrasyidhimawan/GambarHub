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

public class Login extends AppCompatActivity {
    private EditText emailinput;
    private EditText passwordinput;
    private Button loginButton;
    private TextView daftarlink;
    private ImageView logingoogle;

    private final String validEmail = "Gambarhub";
    private final String validPassword = "123456";

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

        daftarlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(v -> {
            String enteredEmail = emailinput.getText().toString().trim();
            String enteredPassword = passwordinput.getText().toString().trim();

            // Validate email and password
            if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
                // Show error message if any field is empty
                Toast.makeText(Login.this, "Please enter email and password!", Toast.LENGTH_SHORT).show();
            } else if (enteredEmail.equals(validEmail) && enteredPassword.equals(validPassword)) {
                // Correct credentials: Navigate to new layout
                Intent intent = new Intent(Login.this, Beranda.class); // Replace with your target activity
                startActivity(intent);
                finish(); // Optional: Close login activity
            } else {
                // Incorrect credentials: Show error message
                Toast.makeText(Login.this, "Invalid email or password. Try again!", Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}