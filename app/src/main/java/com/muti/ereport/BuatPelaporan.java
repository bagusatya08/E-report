package com.muti.ereport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.muti.ereport.model.Laporan;

import java.util.ArrayList;
import java.util.List;

public class BuatPelaporan extends AppCompatActivity {
    EditText alamatEditText;
    EditText topikPelaporanEditText;
    EditText deskripsiPelaporanEditText;
    String nik;
    String nama;
    String telepon;
    String alamat;
    String email;
    String password;
    Button submitBtn;
    List<Laporan> laporanList;
    SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pelaporan);
        initialize();
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // pas ditekan kita cek dulu kalau kolom"nya semua valid yaitu kolom tdk bole kosong, dan
                if(
                alamatEditText.getText().toString().equals("")||
                        topikPelaporanEditText.getText().toString().equals("")||
                        deskripsiPelaporanEditText.getText().toString().equals("")||
                        // deskripsinya harus minimal 10 huruf
                        !(deskripsiPelaporanEditText.getText().toString().length()>=10)){
                    // kalau tidak memenuhi bakalan muncul snackbar bilang mohom periksa data laporan anda
                    Snackbar.make(view,"Mohon periksa data laporan anda",Snackbar.LENGTH_SHORT)
                            .setAction("Tutup", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // jangan sentuh kalau gk ngerti
                                    // default kosong
                                    // snackbar ada tombol tutup ini kosong tapi pas ditekan kalau mau apa" taro disini
                                }
                            }).show();
                }
                else{
                    //bikin laporan baru dari data yg diinput
                    Laporan laporanBaru = new Laporan(nik, alamatEditText.getText().toString(),topikPelaporanEditText.getText().toString(),deskripsiPelaporanEditText.getText().toString());
                    // masukin ke list laporan
                    laporanList.add(laporanBaru);
                    // masukin ke database query
                    mydatabase.execSQL("INSERT INTO tabelPelaporan (nikPelapor, alamatLaporan, tanggalPelaporan, topikPelaporan, deskripsiPelaporan) VALUES('"+laporanBaru.getNik()+"'," +
                            "'"+laporanBaru.getAlamatLaporan()+"','"+laporanBaru.getTanggalPelaporan()+"'," +
                            "'"+laporanBaru.getTopikPelaporan()+"','"+laporanBaru.getDeskripsiPelaporan()+"');");
                    // kosongin balik semua edittext
                    alamatEditText.setText("");
                    topikPelaporanEditText.setText("");
                    deskripsiPelaporanEditText.setText("");
                    // munculin bahwa laporan sukses dibuat
                    Snackbar.make(view,"Sukses Membuat Laporan Pengaduan",Snackbar.LENGTH_SHORT)
                            .setAction("Tutup", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            }).show();
                    // pindah ke activity Laporan Saya
                    Intent toLaporanSayaActivity = new Intent(BuatPelaporan.this,LaporanSaya.class);
                    startActivity(toLaporanSayaActivity);
                    finish();// biar org gbs balik ke sini
                }
            }
        });
    }
    void initialize() {
        alamatEditText = findViewById(R.id.alamatEditText);
        topikPelaporanEditText = findViewById(R.id.topikPelaporanDateEditText);
        deskripsiPelaporanEditText = findViewById(R.id.deskripsiPelaporanEditText);
        submitBtn = findViewById(R.id.submitBtn);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        nik = sharedPreferences.getString("nik", "default_value1");
        nama = sharedPreferences.getString("nama", "default_value2");
        telepon = sharedPreferences.getString("telepon", "default_value3");
        alamat = sharedPreferences.getString("alamat", "default_value4");
        email = sharedPreferences.getString("email", "default_value5");
        password = sharedPreferences.getString("password", "default_value6");

        //database sqllite
        mydatabase = openOrCreateDatabase("pelaporan", MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS tabelPelaporan(" +
                "idPelaporan INTEGER primary key autoincrement,nikPelapor VARCHAR,alamatLaporan VARCHAR,tanggalPelaporan VARCHAR,topikPelaporan VARCHAR,deskripsiPelaporan VARCHAR);");
        //reservasinya kita taro jadi list kosong
        laporanList = new ArrayList<>();
        //kita query data
        Cursor resultSet = mydatabase.rawQuery("Select * from tabelPelaporan", null);
        //1 per satu table yg kita dapat hasilnya kita jadiin variabel laporan
        for (int i = 0; i < resultSet.getCount(); i++) {
            resultSet.moveToPosition(i);
            String idPelaporan = resultSet.getString(0);
            String nik = resultSet.getString(1);
            String alamat = resultSet.getString(2);
            String tanggalPelaporan = resultSet.getString(3);
            String topikPelaporan = resultSet.getString(4);
            String deskripsiPelaporan = resultSet.getString(5);
            //datanya diambil semua masukin jadi laporan
            Laporan laporanBaru = new Laporan(idPelaporan, nik, alamat, tanggalPelaporan, topikPelaporan, deskripsiPelaporan);
            //masukin ke daftar laporan
            laporanList.add(laporanBaru);
        }
    }
}
