package com.example.gambarhub;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class NavbarLogic extends LinearLayout {

    public NavbarLogic(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NavbarLogic(Context context) {
        super(context);
    }

    private void init(Context context) {
        // Inflate the custom layout
        inflate(context, R.layout.navbar, this);

        // Initialize the buttons
        LinearLayout berandaButton = findViewById(R.id.menu_beranda);
        LinearLayout bukuButton = findViewById(R.id.menu_buku);
        LinearLayout profilButton = findViewById(R.id.menu_profil);

        // Set click listeners
        berandaButton.setOnClickListener(v -> handleNavigation(context, 1));
        bukuButton.setOnClickListener(v -> handleNavigation(context, 2));
        profilButton.setOnClickListener(v -> handleNavigation(context, 3));
    }

    private void handleNavigation(Context context, int buttonId) {
        Intent intent = null;

        // Determine navigation based on buttonId and context
        if (context instanceof Beranda) {
            if (buttonId == 2) {
                intent = new Intent(context, activity_add_book.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  // Membuka Baca di task baru
                context.startActivity(intent);
            } else if (buttonId == 3) {
                intent = new Intent(context, Profil.class);
            }
        } else if (context instanceof Profil) {
            if (buttonId == 1) {
                intent = new Intent(context, Beranda.class);
            } else if (buttonId == 2) {
                intent = new Intent(context, activity_add_book.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  // Membuka Baca di task baru
                context.startActivity(intent);
            }
        } else if (context instanceof Baca) {
            if (buttonId == 1) {
                intent = new Intent(context, Beranda.class);
            } else if (buttonId == 3) {
                intent = new Intent(context, Profil.class);
            }
        }
        // Add more cases if needed

        // Start the new activity
        if (intent != null) {
            context.startActivity(intent);
        }
    }
}
