package com.example.bilisimgelisimapp.siniflar;

public class Konular {
    private int konu_id;
    private String konu_ad;
    private String konu_icerik;
    private int bagli_ders_id;

    public Konular() {
    }

    public Konular(String konu_ad) {
        this.konu_ad = konu_ad;
    }

    public int getKonu_id() {
        return konu_id;
    }

    public void setKonu_id(int konu_id) {
        this.konu_id = konu_id;
    }

    public String getKonu_ad() {
        return konu_ad;
    }

    public void setKonu_ad(String konu_ad) {
        this.konu_ad = konu_ad;
    }

    public String getKonu_icerik() {
        return konu_icerik;
    }

    public void setKonu_icerik(String konu_icerik) {
        this.konu_icerik = konu_icerik;
    }

    public int getBagli_ders_id() {
        return bagli_ders_id;
    }

    public void setBagli_ders_id(int bagli_ders_id) {
        this.bagli_ders_id = bagli_ders_id;
    }
}
