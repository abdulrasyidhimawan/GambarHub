package com.example.gambarhub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterBuku extends RecyclerView.Adapter<AdapterBuku.BookViewHolder> {
    private List<Buku> bookList;
    private Context context;

    public AdapterBuku(Context context, List<Buku> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_buku, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Buku book = bookList.get(position);
        holder.titleTextView.setText(book.getTitle());
        holder.authorTextView.setText(book.getAuthor());
        Glide.with(context).load(book.getCoverUrl()).into(holder.coverImageView);

        // Tambahkan onClickListener langsung di sini
        holder.itemView.setOnClickListener(v -> {
            if (context instanceof Beranda) {
                Beranda berandaActivity = (Beranda) context;
                berandaActivity.openBacaActivity(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, authorTextView;
        ImageView coverImageView;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.book_title);
            authorTextView = itemView.findViewById(R.id.book_author);
            coverImageView = itemView.findViewById(R.id.book_cover);
        }
    }
}
