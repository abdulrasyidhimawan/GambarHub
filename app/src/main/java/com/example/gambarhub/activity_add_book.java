package com.example.gambarhub;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class activity_add_book extends AppCompatActivity {

    private EditText etTitle, etAuthor, etDescription, etCoverUrl, etPdfUrl;
    private Button btnAddBook;
    private ImageButton btnBack;

    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        // Inisialisasi Firebase Firestore
        firestore = FirebaseFirestore.getInstance();

        // Inisialisasi View
        etTitle = findViewById(R.id.etTitle);
        etAuthor = findViewById(R.id.etAuthor);
        etDescription = findViewById(R.id.etDescription);
        etCoverUrl = findViewById(R.id.etCoverUrl);
        etPdfUrl = findViewById(R.id.etPdfUrl);
        btnAddBook = findViewById(R.id.btnAddBook);
        btnBack = findViewById(R.id.addbook_back);

        // Set Listener untuk Button
        btnAddBook.setOnClickListener(v -> addBookToFirestore());
        btnBack.setOnClickListener(v -> finish());

    }

    private void addBookToFirestore() {
        // Ambil input dari EditText
        String title = etTitle.getText().toString().trim();
        String author = etAuthor.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String coverUrl = etCoverUrl.getText().toString().trim();
        String pdfUrl = etPdfUrl.getText().toString().trim();

        // Validasi input
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(author) || TextUtils.isEmpty(description)
                || TextUtils.isEmpty(coverUrl) || TextUtils.isEmpty(pdfUrl)) {
            Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Buat data buku sebagai Map
        Map<String, Object> bookData = new HashMap<>();
        bookData.put("title", title);
        bookData.put("author", author);
        bookData.put("description", description);
        bookData.put("coverUrl", coverUrl);
        bookData.put("pdfUrl", pdfUrl);
        bookData.put("bookID", title.toLowerCase().replace(" ", "_")); // ID unik berbasis judul

        // Simpan data ke Firestore di koleksi "books"
        firestore.collection("books")
                .add(bookData)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Buku berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
                    finish(); // Kembali ke activity sebelumnya
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Gagal menambahkan buku: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}