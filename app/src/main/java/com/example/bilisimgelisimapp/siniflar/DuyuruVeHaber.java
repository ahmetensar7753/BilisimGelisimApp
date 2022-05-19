package com.example.bilisimgelisimapp.siniflar;

public class DuyuruVeHaber {
    private int duy_hab_id;
    private String baslik;
    private String duy_hab_ozet;
    private String duy_hab_icerik;
    private String duy_hab_resim_ad;
    private int duy_hab_begeni_say;

    public DuyuruVeHaber() {
    }

    public DuyuruVeHaber(String baslik, String duy_hab_ozet, String duy_hab_resim_ad) {
        this.baslik = baslik;
        this.duy_hab_ozet = duy_hab_ozet;
        this.duy_hab_resim_ad = duy_hab_resim_ad;
    }

    public int getDuy_hab_id() {
        return duy_hab_id;
    }

    public void setDuy_hab_id(int duy_hab_id) {
        this.duy_hab_id = duy_hab_id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getDuy_hab_ozet() {
        return duy_hab_ozet;
    }

    public void setDuy_hab_ozet(String duy_hab_ozet) {
        this.duy_hab_ozet = duy_hab_ozet;
    }

    public String getDuy_hab_icerik() {
        return duy_hab_icerik;
    }

    public void setDuy_hab_icerik(String duy_hab_icerik) {
        this.duy_hab_icerik = duy_hab_icerik;
    }

    public String getDuy_hab_resim_ad() {
        return duy_hab_resim_ad;
    }

    public void setDuy_hab_resim_ad(String duy_hab_resim_ad) {
        this.duy_hab_resim_ad = duy_hab_resim_ad;
    }

    public int getDuy_hab_begeni_say() {
        return duy_hab_begeni_say;
    }

    public void setDuy_hab_begeni_say(int duy_hab_begeni_say) {
        this.duy_hab_begeni_say = duy_hab_begeni_say;
    }
}
