package com.muti.ereport.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muti.ereport.R;
import com.muti.ereport.model.Laporan;

import java.util.List;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.LaporanListViewHolder>{
    public static final int MODE_PRIVATE = 0x0000;
    List<Laporan> laporanList;
    Context context;
    public LaporanAdapter(List<Laporan> reservasiList, Context context) {
        this.laporanList = reservasiList;
        this.context = context;
    }
    @NonNull
    @Override
    public LaporanAdapter.LaporanListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelaporan, parent, false);
        return new  LaporanAdapter.LaporanListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaporanAdapter.LaporanListViewHolder holder, int position) {
        holder.bind(laporanList.get(position));
    }

    @Override
    public int getItemCount() {
        return  laporanList.size();
    }
    // semua UI dari itemnya set disini
    public class LaporanListViewHolder extends RecyclerView.ViewHolder {
        TextView namaTextView;
        TextView ktpTextView;
        TextView alamatTextView;
        TextView tanggalPelaporanTextView;
        TextView topikPelaporanTextView;
        TextView deskripsiPelaporanTextView;
        TextView idPelaporanTextView;

        public LaporanListViewHolder(@NonNull View itemView) {
            super(itemView);
            // ini sama kek initialize()
            namaTextView = itemView.findViewById(R.id.namaTextView);
            ktpTextView = itemView.findViewById(R.id.ktpTextView);
            alamatTextView = itemView.findViewById(R.id.alamatTextView);
            tanggalPelaporanTextView = itemView.findViewById(R.id.tanggalPelaporanTextView);
            topikPelaporanTextView = itemView.findViewById(R.id.topikPelaporanTextView);
            deskripsiPelaporanTextView = itemView.findViewById(R.id.deskripsiPelaporanTextView);
            idPelaporanTextView = itemView.findViewById(R.id.idPelaporanTextView);
        }

        public void bind(Laporan laporan) {
            // masukin data ke UInya
            SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String nama = sharedPreferences.getString("nama", "default_value2");
            namaTextView.setText(nama);
            ktpTextView.setText("NIK: "+laporan.getNik());
            alamatTextView.setText("Telepon : "+laporan.getAlamatLaporan());
            tanggalPelaporanTextView.setText(laporan.getTanggalPelaporan());
            topikPelaporanTextView.setText("Topik : "+laporan.getTopikPelaporan());
            deskripsiPelaporanTextView.setText("Deskripsi : "+laporan.getDeskripsiPelaporan());
            idPelaporanTextView.setText("#LM00" + laporan.getIdPelaporan());
        }
    }
}
