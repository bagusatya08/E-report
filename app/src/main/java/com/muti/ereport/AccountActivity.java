package com.muti.ereport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AccountActivity extends AppCompatActivity {
    ImageView buatPelaporanImageView;
    Button logoutBtn;
    Button editBtn;
    EditText nikEditText;
    EditText namaEditText;
    EditText teleponEditText;
    EditText alamatEditText;
    String nik;
    String nama;
    String telepon;
    String alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initialize();// cek function initialize
        nikEditText.setText(nik);
        namaEditText.setText(nama);
        teleponEditText.setText(telepon);
        alamatEditText.setText(alamat);
        //ini onclick listener dari setiap tombol main menu
        buatPelaporanImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent ke buat pelaporan
                Intent toMainActivity = new Intent(AccountActivity.this,MainActivity.class);
                startActivity(toMainActivity);
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent ke buat pelaporan
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nik", "default_value1");
                editor.putString("nama", "default_value2");
                editor.putString("telepon", "default_value3");
                editor.putString("alamat", "default_value4");
                editor.putString("email", "default_value5");
                editor.putString("password", "default_value6");
                editor.apply();
                Intent toMainActivity = new Intent(AccountActivity.this,LoginActivity.class);
                startActivity(toMainActivity);
                finish();
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent ke buat pelaporan
                Intent toMainActivity = new Intent(AccountActivity.this,EditAccountActivity.class);
                startActivity(toMainActivity);
                finish();
            }
        });
    }
    void initialize(){// semua yg di initialize itu untuk masukin variabel cari UInya
        buatPelaporanImageView = findViewById(R.id.iV5);
        nikEditText = findViewById(R.id.nikEditText);
        namaEditText = findViewById(R.id.namaEditText);
        teleponEditText = findViewById(R.id.teleponEditText);
        alamatEditText = findViewById(R.id.alamatEditText);
        logoutBtn = findViewById(R.id.btnLogout);
        editBtn = findViewById(R.id.btnEdit);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        nik = sharedPreferences.getString("nik", "default_value1");
        nama = sharedPreferences.getString("nama", "default_value2");
        telepon = sharedPreferences.getString("telepon", "default_value3");
        alamat = sharedPreferences.getString("alamat", "default_value4");
    }
}