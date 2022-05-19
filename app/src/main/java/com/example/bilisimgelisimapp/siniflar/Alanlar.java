package com.example.bilisimgelisimapp.siniflar;

import java.util.ArrayList;

public class Alanlar {

    private int alan_id;
    private String alan_ad;
    private String alan_ozet;
    private String alan_proje_baslangic;
    private String alan_proje_orta;
    private String alan_proje_ileri;
    private String alan_yazili_kaynak;
    private String alan_video_kaynak;
    private int alan_calisma_suresi;
    private String alan_diller;
    private Double alan_zorluk;
    private String alan_terimler;
    private String alan_olmazsa_olmazlar;

    public Alanlar() {
    }

    public Alanlar(String alan_ad) {
        this.alan_ad = alan_ad;
    }

    public int getAlan_id() {
        return alan_id;
    }

    public void setAlan_id(int alan_id) {
        this.alan_id = alan_id;
    }

    public String getAlan_ad() {
        return alan_ad;
    }

    public void setAlan_ad(String alan_ad) {
        this.alan_ad = alan_ad;
    }

    public String getAlan_ozet() {
        return alan_ozet;
    }

    public void setAlan_ozet(String alan_ozet) {
        this.alan_ozet = alan_ozet;
    }

    public String getAlan_proje_baslangic() {
        return alan_proje_baslangic;
    }

    public void setAlan_proje_baslangic(String alan_proje_baslangic) {
        this.alan_proje_baslangic = alan_proje_baslangic;
    }

    public String getAlan_proje_orta() {
        return alan_proje_orta;
    }

    public void setAlan_proje_orta(String alan_proje_orta) {
        this.alan_proje_orta = alan_proje_orta;
    }

    public String getAlan_proje_ileri() {
        return alan_proje_ileri;
    }

    public void setAlan_proje_ileri(String alan_proje_ileri) {
        this.alan_proje_ileri = alan_proje_ileri;
    }

    public String getAlan_yazili_kaynak() {
        return alan_yazili_kaynak;
    }

    public void setAlan_yazili_kaynak(String alan_yazili_kaynak) {
        this.alan_yazili_kaynak = alan_yazili_kaynak;
    }

    public String getAlan_video_kaynak() {
        return alan_video_kaynak;
    }

    public void setAlan_video_kaynak(String alan_video_kaynak) {
        this.alan_video_kaynak = alan_video_kaynak;
    }

    public int getAlan_calisma_suresi() {
        return alan_calisma_suresi;
    }

    public void setAlan_calisma_suresi(int alan_calisma_suresi) {
        this.alan_calisma_suresi = alan_calisma_suresi;
    }

    public String getAlan_diller() {
        return alan_diller;
    }

    public void setAlan_diller(String alan_diller) {
        this.alan_diller = alan_diller;
    }

    public Double getAlan_zorluk() {
        return alan_zorluk;
    }

    public void setAlan_zorluk(Double alan_zorluk) {
        this.alan_zorluk = alan_zorluk;
    }

    public String getAlan_terimler() {
        return alan_terimler;
    }

    public void setAlan_terimler(String alan_terimler) {
        this.alan_terimler = alan_terimler;
    }

    public String getAlan_olmazsa_olmazlar() {
        return alan_olmazsa_olmazlar;
    }

    public void setAlan_olmazsa_olmazlar(String alan_olmazsa_olmazlar) {
        this.alan_olmazsa_olmazlar = alan_olmazsa_olmazlar;
    }
}
