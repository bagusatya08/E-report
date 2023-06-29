package com.muti.ereport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    CardView buatPelaporanCardView;
    CardView laporanSayaCardView;
    ImageView akunSayaImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();// cek function initialize
        //ini onclick listener dari setiap tombol main menu
        buatPelaporanCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent ke buat pelaporan
                Intent toBuatPelaporan = new Intent(MainActivity.this,BuatPelaporan.class);
                startActivity(toBuatPelaporan);
            }
        });
        laporanSayaCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // intent ke laporan saya
                Intent toLaporanSaya = new Intent(MainActivity.this,LaporanSaya.class);
                startActivity(toLaporanSaya);
            }
        });
        akunSayaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // intent ke akun saya
                Intent toAkunSaya = new Intent(MainActivity.this,AccountActivity.class);
                startActivity(toAkunSaya);
            }
        });
    }
    void initialize(){// semua yg di initialize itu untuk masukin variabel cari UInya
        buatPelaporanCardView = findViewById(R.id.buatPelaporanCardView);
        laporanSayaCardView = findViewById(R.id.laporanSayaCardView);
        akunSayaImageView = findViewById(R.id.iV6);
    }
}