package com.muti.ereport.model;

import android.content.Context;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Laporan implements Serializable {
    String nik;
    String alamatLaporan;
    Date tanggalPelaporan;
    String idPelaporan;
    String topikPelaporan;
    String deskripsiPelaporan;

    public Laporan(String nik, String alamatLaporan, String topikPelaporan, String deskripsiPelaporan) {
        this.nik = nik;
        this.alamatLaporan = alamatLaporan;
        this.topikPelaporan = topikPelaporan;
        this.deskripsiPelaporan = deskripsiPelaporan;
        this.tanggalPelaporan = new Date();
        this.idPelaporan = "003";
    }

    public Laporan(String idPelaporan, String nik, String alamatLaporan, String tanggalPelaporan,  String topikPelaporan, String deskripsiPelaporan) {
        this.nik = nik;
        this.alamatLaporan = alamatLaporan;
        this.topikPelaporan = topikPelaporan;
        this.deskripsiPelaporan = deskripsiPelaporan;
        this.idPelaporan = idPelaporan;
        setTanggalPelaporan(tanggalPelaporan);
    }

    public String convertDateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    public Date convertStringToDate(String dateString){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date temp = dateFormat.parse(dateString);
            return temp;
        } catch (ParseException e) {
            return null;
        }
    }

    public void setTanggalPelaporan(String dateString) {
        this.tanggalPelaporan = convertStringToDate(dateString);
    }

    public String getTanggalPelaporan(){
        return convertDateToString(this.tanggalPelaporan);
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String ktp) {
        this.nik = ktp;
    }

    public String getAlamatLaporan() {
        return alamatLaporan;
    }

    public void setAlamatLaporan(String alamatLaporan) {
        this.alamatLaporan = alamatLaporan;
    }

    public String getIdPelaporan() {
        return idPelaporan;
    }

    public void setIdPelaporan(String idPelaporan) {
        this.idPelaporan = idPelaporan;
    }

    public String getTopikPelaporan() {
        return topikPelaporan;
    }

    public void setTopikPelaporan(String topikPelaporan) {
        this.topikPelaporan = topikPelaporan;
    }

    public String getDeskripsiPelaporan() {
        return deskripsiPelaporan;
    }

    public void setDeskripsiPelaporan(String deskripsiPelaporan) {
        this.deskripsiPelaporan = deskripsiPelaporan;
    }
}

