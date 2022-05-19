package com.example.bilisimgelisimapp.siniflar;

public class AlanPratikBilgiler {

    private int bilgi_id;
    private String bilgi_baslik;
    private String bilgi_icerik;
    private int bagli_alan_id;

    public AlanPratikBilgiler() {
    }

    public AlanPratikBilgiler(String bilgi_baslik) {
        this.bilgi_baslik = bilgi_baslik;
    }

    public int getBilgi_id() {
        return bilgi_id;
    }

    public void setBilgi_id(int bilgi_id) {
        this.bilgi_id = bilgi_id;
    }

    public String getBilgi_baslik() {
        return bilgi_baslik;
    }

    public void setBilgi_baslik(String bilgi_baslik) {
        this.bilgi_baslik = bilgi_baslik;
    }

    public String getBilgi_icerik() {
        return bilgi_icerik;
    }

    public void setBilgi_icerik(String bilgi_icerik) {
        this.bilgi_icerik = bilgi_icerik;
    }

    public int getBagli_alan_id() {
        return bagli_alan_id;
    }

    public void setBagli_alan_id(int bagli_alan_id) {
        this.bagli_alan_id = bagli_alan_id;
    }
}
