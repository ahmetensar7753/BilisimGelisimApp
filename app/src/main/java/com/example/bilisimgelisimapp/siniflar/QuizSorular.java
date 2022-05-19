package com.example.bilisimgelisimapp.siniflar;

public class QuizSorular {

    private int soru_id;
    private String soru;
    private String dogru_cevap;
    private String cevap2;
    private String cevap3;
    private String cevap4;

    public QuizSorular() {
    }

    public QuizSorular(String soru, String dogru_cevap, String cevap2, String cevap3, String cevap4) {
        this.soru = soru;
        this.dogru_cevap = dogru_cevap;
        this.cevap2 = cevap2;
        this.cevap3 = cevap3;
        this.cevap4 = cevap4;
    }

    public int getSoru_id() {
        return soru_id;
    }

    public void setSoru_id(int soru_id) {
        this.soru_id = soru_id;
    }

    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public String getDogru_cevap() {
        return dogru_cevap;
    }

    public void setDogru_cevap(String dogru_cevap) {
        this.dogru_cevap = dogru_cevap;
    }

    public String getCevap2() {
        return cevap2;
    }

    public void setCevap2(String cevap2) {
        this.cevap2 = cevap2;
    }

    public String getCevap3() {
        return cevap3;
    }

    public void setCevap3(String cevap3) {
        this.cevap3 = cevap3;
    }

    public String getCevap4() {
        return cevap4;
    }

    public void setCevap4(String cevap4) {
        this.cevap4 = cevap4;
    }
}
