package com.example.bilisimgelisimapp.siniflar;

public class Oneriler {
    private int oneri_id;
    private String oneri_icerik;
    private String oneri_turu;
    private String oneri_kaynak;

    public Oneriler() {
    }

    public Oneriler(String oneri_icerik, String oneri_turu, String oneri_kaynak) {
        this.oneri_icerik = oneri_icerik;
        this.oneri_turu = oneri_turu;
        this.oneri_kaynak = oneri_kaynak;
    }

    public int getOneri_id() {
        return oneri_id;
    }

    public void setOneri_id(int oneri_id) {
        this.oneri_id = oneri_id;
    }

    public String getOneri_icerik() {
        return oneri_icerik;
    }

    public void setOneri_icerik(String oneri_icerik) {
        this.oneri_icerik = oneri_icerik;
    }

    public String getOneri_turu() {
        return oneri_turu;
    }

    public void setOneri_turu(String oneri_turu) {
        this.oneri_turu = oneri_turu;
    }

    public String getOneri_kaynak() {
        return oneri_kaynak;
    }

    public void setOneri_kaynak(String oneri_kaynak) {
        this.oneri_kaynak = oneri_kaynak;
    }
}
