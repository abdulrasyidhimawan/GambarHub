package com.example.gambarhub;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class detailbuku extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailbuku); // Pastikan layout ini sesuai

        // Ambil data dari Intent
        Buku book = getIntent().getParcelableExtra("buku");
        if (book == null) {
            finish(); // Jika data kosong, tutup activity
            return;
        }

        // Hubungkan View dengan ID dari XML
        TextView titleTextView = findViewById(R.id.detailbuku_labelJudulBuku);
        TextView descriptionTextView = findViewById(R.id.detailbuku_viewSinopsisBuku);
        ImageView coverImageView = findViewById(R.id.detailbuku_viewCoverBuku);
        Button readButton = findViewById(R.id.detailbuku_tombolBaca);

        // Atur data ke Views
        titleTextView.setText(book.getTitle());
        setDescription(descriptionTextView, book.getDescription());
        Glide.with(this).load(book.getCoverUrl()).into(coverImageView);

        // Atur click listener untuk tombol baca
        readButton.setOnClickListener(v -> {
            Intent intent = new Intent(detailbuku.this, Baca.class);
            intent.putExtra("pdfUrl", book.getPdfUrl());
            startActivity(intent);
        });
    }
    private void setDescription(TextView textView, String description) {
        if (description == null || description.trim().isEmpty()) {
            textView.setText("Deskripsi tidak tersedia."); // Teks default jika deskripsi kosong
        } else {
            textView.setText(description);
        }
    }

}
