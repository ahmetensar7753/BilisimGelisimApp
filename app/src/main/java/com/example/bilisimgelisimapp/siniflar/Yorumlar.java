package com.example.bilisimgelisimapp.siniflar;

public class Yorumlar {
    private int yorum_id;
    private String yorum_baslik;
    private String yorum_icerik;
    private int bagli_duy_hab_id;
    private int bagli_kullanici_id;
    private String yorumSahibiAd;

    public Yorumlar() {
    }

    public Yorumlar(String yorum_baslik, String yorum_icerik) {
        this.yorum_baslik = yorum_baslik;
        this.yorum_icerik = yorum_icerik;
    }

    public int getYorum_id() {
        return yorum_id;
    }

    public String getYorumSahibiAd() {
        return yorumSahibiAd;
    }

    public void setYorumSahibiAd(String yorumSahibiAd) {
        this.yorumSahibiAd = yorumSahibiAd;
    }

    public void setYorum_id(int yorum_id) {
        this.yorum_id = yorum_id;
    }

    public String getYorum_baslik() {
        return yorum_baslik;
    }

    public void setYorum_baslik(String yorum_baslik) {
        this.yorum_baslik = yorum_baslik;
    }

    public String getYorum_icerik() {
        return yorum_icerik;
    }

    public void setYorum_icerik(String yorum_icerik) {
        this.yorum_icerik = yorum_icerik;
    }

    public int getBagli_duy_hab_id() {
        return bagli_duy_hab_id;
    }

    public void setBagli_duy_hab_id(int bagli_duy_hab_id) {
        this.bagli_duy_hab_id = bagli_duy_hab_id;
    }

    public int getBagli_kullanici_id() {
        return bagli_kullanici_id;
    }

    public void setBagli_kullanici_id(int bagli_kullanici_id) {
        this.bagli_kullanici_id = bagli_kullanici_id;
    }
}
