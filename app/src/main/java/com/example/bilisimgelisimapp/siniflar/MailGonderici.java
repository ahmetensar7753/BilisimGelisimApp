package com.example.bilisimgelisimapp.siniflar;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailGonderici extends AsyncTask<Void,Void,String> {

    private Context context;
    private Session session;

    // Mesajın gideceği mail adresi, konu ve mesaj
    private String alici_mail_adresi;
    private String konu;
    private String mesaj;

    private ProgressDialog progressDialog;

    public MailGonderici(Context context, String alici_mail_adresi, String konu, String mesaj){
        this.context = context;
        this.alici_mail_adresi = alici_mail_adresi;
        this.konu = konu;
        this.mesaj = mesaj;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Mail giderken kullanıcıyı bekleten progress dialog
        progressDialog = ProgressDialog.show(context,"Mail Yollanıyor","Lütfen Bekleyiniz...",false,false);
    }

    @Override
    protected String doInBackground(Void... voids) {

        Properties props = new Properties();

        //Mail konfigürasyon ayarları gmail kullandığımız için bunlar sabit kalsın
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    // Yapılandırma verilerini bu şekilde alıp mailimize giriş yapıyoruz
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(YapilandirmaVerileri.MAIL, YapilandirmaVerileri.SIFRE);
                    }

                });

        try {

            MimeMessage mm = new MimeMessage(session);

            //Gönnderici adresi
            mm.setFrom(new InternetAddress(YapilandirmaVerileri.MAIL));

            //Alıcı adresi
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(alici_mail_adresi));

            //Konu
            mm.setSubject(konu);

            // Normal Mesaj
            //mm.setText(mesaj);

            // UTF-8 Destekli Mesajı Eklediğimiz Kısım
            mm.setContent(mesaj, "text/plain; charset=UTF-8");

            //Mail gönderme anı
            Transport.send(mm);

            return "basarili";

        } catch (MessagingException e) {

            Log.e("hata","Hata : "+e.toString());
            e.printStackTrace();

            return null;

        }

    }

    @Override
    protected void onPostExecute(String sonuc) {

        progressDialog.dismiss();

        // Eğer dönen sonuç null değilse mesaj gitmiştir
        if(sonuc != null){

            Toast.makeText(context,"İlgili Bilgiler Mailinize Gönderildi!",Toast.LENGTH_LONG).show();

        }else{

            Toast.makeText(context,"İşlem Başarısız, Daha Sonra Tekrar Deneyiniz!",Toast.LENGTH_LONG).show();

        }

    }

}