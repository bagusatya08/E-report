package com.muti.ereport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.muti.ereport.adapter.LaporanAdapter;
import com.muti.ereport.listener.RecyclerItemClickListener;
import com.muti.ereport.model.Laporan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LaporanSaya extends AppCompatActivity {
    SQLiteDatabase mydatabase;
    RecyclerView laporanListRecyclerView;
    List<Laporan> laporanList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_saya);
        initialize();
        laporanListRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(laporanListRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View inView, int inPosition) throws IOException {
                Toast.makeText(getApplicationContext(), "Topik: " + laporanList.get(inPosition).getTopikPelaporan(), Toast.LENGTH_SHORT).show();
            }
        }));
    }
    void initialize(){// masukin UI ke variable
        laporanListRecyclerView = findViewById(R.id.recyclerLaporanList);
        loadDatabase();// kita ambil data dri database dulu cek function load database
        laporanListRecyclerView.setAdapter(new LaporanAdapter(laporanList, LaporanSaya.this));// set laporan adapter
    }

    void loadDatabase(){
        // konek database
        mydatabase = openOrCreateDatabase("pelaporan", MODE_PRIVATE, null);
        //kalau tablenya gak ada kita buat
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS tabelPelaporan(" +
                "idPelaporan INTEGER primary key autoincrement,nikPelapor VARCHAR,alamatLaporan VARCHAR,tanggalPelaporan VARCHAR,topikPelaporan VARCHAR,deskripsiPelaporan VARCHAR);");
        //laporannya kita taro jadi list kosong
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