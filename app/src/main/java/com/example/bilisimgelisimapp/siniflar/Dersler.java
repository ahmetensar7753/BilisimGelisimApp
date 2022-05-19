package com.example.bilisimgelisimapp.siniflar;

import java.util.ArrayList;

public class Dersler {
    private int ders_id;
    private String ders_ad;
    private Double ders_puan;
    private String ders_ozet;
    private Double zorluk_derecesi;
    private String kullanim_alanlari;
    private String kullanilan_diller;
    private Integer calisma_suresi;
    private String terimler;
    private Double puan_toplam;
    private Integer verilen_oy_adet;

    public Dersler() {
    }

    public Double getPuan_toplam() {
        return puan_toplam;
    }

    public void setPuan_toplam(Double puan_toplam) {
        this.puan_toplam = puan_toplam;
    }

    public Integer getVerilen_oy_adet() {
        return verilen_oy_adet;
    }

    public void setVerilen_oy_adet(Integer verilen_oy_adet) {
        this.verilen_oy_adet = verilen_oy_adet;
    }

    public Dersler(String ders_ad) {
        this.ders_ad = ders_ad;
    }

    public int getDers_id() {
        return ders_id;
    }

    public void setDers_id(int ders_id) {
        this.ders_id = ders_id;
    }

    public String getDers_ad() {
        return ders_ad;
    }

    public void setDers_ad(String ders_ad) {
        this.ders_ad = ders_ad;
    }

    public Double getDers_puan() {
        return ders_puan;
    }

    public void setDers_puan(Double ders_puan) {
        this.ders_puan = ders_puan;
    }

    public String getDers_ozet() {
        return ders_ozet;
    }

    public void setDers_ozet(String ders_ozet) {
        this.ders_ozet = ders_ozet;
    }

    public Double getZorluk_derecesi() {
        return zorluk_derecesi;
    }

    public void setZorluk_derecesi(Double zorluk_derecesi) {
        this.zorluk_derecesi = zorluk_derecesi;
    }

    public String getKullanim_alanlari() {
        return kullanim_alanlari;
    }

    public void setKullanim_alanlari(String kullanim_alanlari) {
        this.kullanim_alanlari = kullanim_alanlari;
    }

    public String getKullanilan_diller() {
        return kullanilan_diller;
    }

    public void setKullanilan_diller(String kullanilan_diller) {
        this.kullanilan_diller = kullanilan_diller;
    }

    public Integer getCalisma_suresi() {
        return calisma_suresi;
    }

    public void setCalisma_suresi(Integer calisma_suresi) {
        this.calisma_suresi = calisma_suresi;
    }

    public String getTerimler() {
        return terimler;
    }

    public void setTerimler(String terimler) {
        this.terimler = terimler;
    }
}
