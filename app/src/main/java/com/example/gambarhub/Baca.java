package com.example.gambarhub;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Baca extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ambil referensi ke WebView
        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);  // Aktifkan JavaScript
        webView.setWebViewClient(new WebViewClient());  // Gunakan WebViewClient untuk tetap di dalam aplikasi

        // Dapatkan ID Buku atau dokumen dari Intent
        String bookId = getIntent().getStringExtra("bookId");

        // Periksa apakah bookId ada
        if (bookId == null || bookId.isEmpty()) {
            Toast.makeText(this, "Book ID is missing", Toast.LENGTH_SHORT).show();
            finish();  // Selesai jika bookId tidak ada
            return;
        }

        // Ambil URL PDF dari Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("books").document(bookId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Buku buku = documentSnapshot.toObject(Buku.class);
                        if (buku != null) {
                            String pdfUrl = buku.getPdfUrl();  // Dapatkan pdfUrl dari objek Buku

                            if (pdfUrl != null && !pdfUrl.isEmpty()) {
                                // Muat PDF ke dalam WebView menggunakan Google Docs Viewer
                                webView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + pdfUrl);
                            } else {
                                Toast.makeText(Baca.this, "PDF URL not available", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Baca.this, "Error fetching book data", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Baca.this, "Book not found", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(Baca.this, "Error loading PDF", Toast.LENGTH_SHORT).show());
    }
}
