package com.example.gambarhub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Beranda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        // Inisialisasi elemen UI
        TextView username = findViewById(R.id.beranda_labelusername);
//        SearchView searchBar = findViewById(R.id.search_bar);
        RecyclerView listbuku = findViewById(R.id.listbuku);

        // Firebase Firestore setup
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<Buku> bookList = new ArrayList<>();

        // Inisialisasi Adapter
        AdapterBuku adapter = new AdapterBuku(this, bookList);
        listbuku.setAdapter(adapter);

        // Set RecyclerView's LayoutManager
        listbuku.setLayoutManager(new LinearLayoutManager(this));

        // Fetch data dari Firebase Firestore
        db.collection("books")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        bookList.clear(); // Kosongkan daftar sebelum menambahkan
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Buku book = document.toObject(Buku.class); // Konversi dokumen menjadi objek Buku
                            bookList.add(book); // Tambahkan ke daftar
                        }
                        adapter.notifyDataSetChanged(); // Perbarui adapter setelah data diubah
                    } else {
                        Log.e("FirebaseError", "Error: ", task.getException());
                    }
                });

        // Apply window insets untuk edge-to-edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metode untuk membuka Activity Baca
    public void openBacaActivity(Buku book) {
        Intent intent = new Intent(this, Baca.class);
        intent.putExtra("Buku", book); // Mengirim objek buku
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
