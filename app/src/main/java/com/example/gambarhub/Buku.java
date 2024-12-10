package com.example.gambarhub;

import android.os.Parcel;
import android.os.Parcelable;

public class Buku implements Parcelable {
    private String title;
    private String author;
    private String coverUrl;
    private String pdfUrl;
    private String description;

    // Konstruktor kosong (penting untuk Firebase)
    public Buku() {}

    // Konstruktor dengan semua parameter
    public Buku(String title, String author, String coverUrl, String pdfUrl, String description) {
        this.title = title;
        this.author = author;
        this.coverUrl = coverUrl;
        this.pdfUrl = pdfUrl;
        this.description = description;
    }

    // Getter dan Setter untuk setiap atribut
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Implementasi Parcelable
    protected Buku(Parcel in) {
        title = in.readString();
        author = in.readString();
        coverUrl = in.readString();
        pdfUrl = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(coverUrl);
        dest.writeString(pdfUrl);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Buku> CREATOR = new Creator<Buku>() {
        @Override
        public Buku createFromParcel(Parcel in) {
            return new Buku(in);
        }

        @Override
        public Buku[] newArray(int size) {
            return new Buku[size];
        }
    };
}
