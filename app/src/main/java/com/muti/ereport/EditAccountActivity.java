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
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class EditAccountActivity extends AppCompatActivity {
    ImageView buatPelaporanImageView;
    Button saveBtn;
    EditText nikEditText;
    EditText namaEditText;
    EditText teleponEditText;
    EditText alamatEditText;
    String nik;
    String nama;
    String telepon;
    String alamat;
    String email;
    String password;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        initialize();// cek function initialize
        nikEditText.setText(nik);
        namaEditText.setText(nama);
        teleponEditText.setText(telepon);
        alamatEditText.setText(alamat);
        //ini onclick listener dari setiap tombol main menu
        buatPelaporanImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMainActivity = new Intent(EditAccountActivity.this,MainActivity.class);
                startActivity(toMainActivity);
                finish();// biar org gbs balik ke sini
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(namaEditText.getText().toString().equals("")||
                        teleponEditText.getText().toString().equals("")||
                        alamatEditText.getText().toString().equals("")){
                    Snackbar.make(v,"Mohon Periksa Data Pembaruan Anda!",Snackbar.LENGTH_SHORT)
                            .setAction("Tutup", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            }).show();
                }
                else{
                    Boolean update = dbHelper.updateDataUsers(email, namaEditText.getText().toString(), teleponEditText.getText().toString(), alamatEditText.getText().toString());
                    if(update) {
                        dbHelper.getDataUsers(getApplicationContext(), email, password);
                        Intent toAccountActivity = new Intent(EditAccountActivity.this, AccountActivity.class);
                        startActivity(toAccountActivity);
                        finish();// biar org gbs balik ke sini
                        Snackbar.make(v, "Sukses Update Data Akun!", Snackbar.LENGTH_SHORT)
                                .setAction("Tutup", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                }).show();
                    }
                    else {
                        Snackbar.make(v, "Gagal Update Data Akun!", Snackbar.LENGTH_SHORT)
                                .setAction("Tutup", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                }).show();
                    }
                }
            }
        });
    }
    void initialize(){// semua yg di initialize itu untuk masukin variabel cari UInya
        buatPelaporanImageView = findViewById(R.id.iV5);
        nikEditText = findViewById(R.id.nikEditText);
        namaEditText = findViewById(R.id.namaEditText);
        teleponEditText = findViewById(R.id.teleponEditText);
        alamatEditText = findViewById(R.id.alamatEditText);
        saveBtn = findViewById(R.id.btnSave);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        nik = sharedPreferences.getString("nik", "default_value1");
        nama = sharedPreferences.getString("nama", "default_value2");
        telepon = sharedPreferences.getString("telepon", "default_value3");
        alamat = sharedPreferences.getString("alamat", "default_value4");
        email = sharedPreferences.getString("email", "default_value5");
        password = sharedPreferences.getString("password", "default_value6");
        dbHelper = new DBHelper(this);
    }
}