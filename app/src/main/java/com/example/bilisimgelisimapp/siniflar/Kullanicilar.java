package com.example.bilisimgelisimapp.siniflar;

public class Kullanicilar {
    private int kullanici_id;
    private byte kullanici_yetki;
    private String kullanici_ad;
    private String kullanici_soyad;
    private String e_mail;
    private String kullanici_sifre;
    private String kullanici_bolum;
    private int profil_puani;
    private int cozulen_test_sayisi;

    public Kullanicilar() {
    }

    public Kullanicilar(String kullanici_ad, String kullanici_soyad, String e_mail, String kullanici_sifre, String kullanici_bolum) {
        this.kullanici_ad = kullanici_ad;
        this.kullanici_soyad = kullanici_soyad;
        this.e_mail = e_mail;
        this.kullanici_sifre = kullanici_sifre;
        this.kullanici_bolum = kullanici_bolum;
    }

    //yeni olmayan kullanicilar çekilirken kullanılan cons. lardan birisi.
    public Kullanicilar(int kullanici_id, String e_mail, String kullanici_sifre) {
        this.kullanici_id = kullanici_id;
        this.e_mail = e_mail;
        this.kullanici_sifre = kullanici_sifre;
    }

    public int getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(int kullanici_id) {
        this.kullanici_id = kullanici_id;
    }

    public byte getKullanici_yetki() {
        return kullanici_yetki;
    }

    public void setKullanici_yetki(byte kullanici_yetki) {
        this.kullanici_yetki = kullanici_yetki;
    }

    public String getKullanici_ad() {
        return kullanici_ad;
    }

    public void setKullanici_ad(String kullanici_ad) {
        this.kullanici_ad = kullanici_ad;
    }

    public String getKullanici_soyad() {
        return kullanici_soyad;
    }

    public void setKullanici_soyad(String kullanici_soyad) {
        this.kullanici_soyad = kullanici_soyad;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getKullanici_sifre() {
        return kullanici_sifre;
    }

    public void setKullanici_sifre(String kullanici_sifre) {
        this.kullanici_sifre = kullanici_sifre;
    }

    public String getKullanici_bolum() {
        return kullanici_bolum;
    }

    public void setKullanici_bolum(String kullanici_bolum) {
        this.kullanici_bolum = kullanici_bolum;
    }

    public int getProfil_puani() {
        return profil_puani;
    }

    public void setProfil_puani(int profil_puani) {
        this.profil_puani = profil_puani;
    }

    public int getCozulen_test_sayisi() {
        return cozulen_test_sayisi;
    }

    public void setCozulen_test_sayisi(int cozulen_test_sayisi) {
        this.cozulen_test_sayisi = cozulen_test_sayisi;
    }
}
