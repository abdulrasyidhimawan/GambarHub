package com.example.gambarhub;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Baca extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        // Ambil data Buku dari Intent
        Buku book = getIntent().getParcelableExtra("Buku");

        if (book != null) {
            String pdfUrl = book.getPdfUrl();
            if (pdfUrl != null && !pdfUrl.isEmpty()) {
                webView.loadUrl(pdfUrl);
            } else {
                Toast.makeText(this, "PDF URL is empty", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Book data is missing", Toast.LENGTH_SHORT).show();
            webView.loadUrl("about:blank"); // Tidak memuat apapun jika data buku tidak ditemukan
        }
    }


}
