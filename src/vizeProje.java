//package mailapi;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;
import java.util.Scanner;


/*class emailsender {


    public static void main(String [] alıcılar) {
        // SMTP ayarları
        String host = "smtp.gmail.com";
        String kullaniciAdi = "abdlbktpc7@gmail.com";
        String sifre = "gqtcpwotfutfyssp";
        int port = 587;

        // Gönderici bilgileri
        String gonderenAdi = "abdulbaki";
        String gonderenEmail = "abdlbktpc7@gmail.com";

        // Alıcılar
        String alicilar = "adam43adam83@gmail.com\n" + "Abdulbaki Topçu abdlbktpc7@gmail.com";

        // Mesaj içeriği
        String konu = "gelirse şükredin";
        String icerik = "Alllllllaaaaaaahhhh";

        // Ayarları yapılandırma
        Properties ozellikler = new Properties();
        ozellikler.put("mail.smtp.auth", "true");
        ozellikler.put("mail.smtp.starttls.enable", "true");
        ozellikler.put("mail.smtp.host", host);
        ozellikler.put("mail.smtp.port", port);

        // Oturum oluşturma ve kimlik doğrulama
        Session oturum = Session.getInstance(ozellikler, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(kullaniciAdi, sifre);
            }
        });

        try {
            // Mesaj oluşturma
            MimeMessage mesaj = new MimeMessage(oturum);
            mesaj.setFrom(new InternetAddress(gonderenEmail, gonderenAdi));
            for (String alici : alıcılar) {
                mesaj.addRecipient(Message.RecipientType.TO, new InternetAddress(alici));
            }
            mesaj.setSubject(konu);
            mesaj.setText(icerik);

            // Mesajı gönderme
            Transport.send(mesaj);
            System.out.println("Mesaj gönderildi.");
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println("Mesaj gönderilemedi. Hata: " + e.getMessage());
        }
    }
}
*/

public class vizeProje {
    public static void main(String[] args) throws IOException {
        System.out.println("Yapmak istediginiz islemi seciniz \n" + "1- Elit üye ekleme\n" +// bu bölümde kullanıcıhangi işlemi yapmak istediğine dair seçenekler sunuyoruz
                "2- Genel Üye ekleme\n" +
                "3- Mail Gönderme");
        Scanner scanner = new Scanner(System.in);
        int secim = scanner.nextInt();
        scanner.nextLine();
        System.out.println("seciminiz:" + secim);
        String emailBilgi = null;                                                            //İlerde kullanmak için 'emailBilgi' tanımlıyoruz
        FileReader FileReader = new FileReader("Kullanıcılar.txt");                  //ileride kullanmak için 'kullanıcı txt' okuma yapıyoruz
        BufferedReader geciciOkuyucu = new BufferedReader(FileReader);
        int boyut = 100;                                                                     // burada kısıtlama olmaması için boyutumuzu yüksek tuttuk
        String[] araBellek = new String[boyut];
        int n=0;
        File file = new File("Kullanıcılar.txt");
        FileWriter yazici = new FileWriter(file, true);                               //dosyamızı açıyoruz
        if (!file.exists()) {
            file.createNewFile();
        }
        boolean birinciGecis = false;                                                        //iki farklı başlığımız olduğundan iki farklı geçişimiz olacak
        boolean ikinciGecis = false;

        if (secim == 1) {
            System.out.println("ELİT ÜYE EKLEME SAYFASI\n" + "Lütfen istenilen bilgileri giriniz"); //burada secim yapıyoruz
            System.out.println("İsim ve soyisminizi giriniz");
            String isim = scanner.nextLine();
            System.out.println("Lütfen mail adresinizi giriniz");
            String mail = scanner.nextLine();

            RandomAccessFile dosya = new RandomAccessFile("Kullanıcılar.txt", "rw");  //burada okuyup yazdırma işlemi yapıyoruz

// ELİT UYELER başlığından sonra yeni veriler eklenecek
            dosya.seek(13);
            byte[] eliteUyeler = new byte[250]; // yeni veri eklenecek boşluk
            dosya.read(eliteUyeler); // kalan verileri okuyarak boşluğu doldurur
            dosya.seek(13);
            dosya.writeBytes("\n" + isim +"\t"+ mail ); // yeni verileri yazar
            dosya.write(eliteUyeler); // geri kalan verileri yazar

        }






        else if (secim == 2) {
                    System.out.println("GENEL ÜYE EKLEME SAYFASI\n" + "Lütfen istenilen bilgileri giriniz");
                    System.out.println("İsim ve soyisminizi giriniz");
                    String isim = scanner.nextLine();
                    System.out.println("Lütfen mail adresinizi giriniz");
                    String mail = scanner.nextLine();
                    FileWriter fileWriter = new FileWriter(file, true);
                    BufferedWriter bWriter = new BufferedWriter(fileWriter);
                    bWriter.write(isim + "\t" + mail + "\n");
                    bWriter.close();

                }
        else if (secim == 3) {
                    System.out.println("Mail Gönderme Sayfası\n" + "Seçimini yapınız\n" + "1- Elit üyelere mail\n" +
                            "2- Genel üyelere mail\n" +
                            "3- Tüm üyelere mail");
                    int altSecim = scanner.nextInt();


                    switch (altSecim) {

                        case 1 -> {

                            while ((emailBilgi = geciciOkuyucu.readLine()) != null) {
                                if (!birinciGecis && emailBilgi.contains("ELİT UYELER")) {    //birincigecis false idi o yuzden burada true'ya döndü
                                    // ELİT UYELER kısmını geçtik                             // email.contains ile Elit uyeler içeriyor
                                    birinciGecis = true;
                                } else if (birinciGecis && emailBilgi.contains("GENEL UYELER")) {//genel uyelerde duruyor
                                    // GENEL UYELER kısmına geldik, döngüden çıkabiliriz
                                    break;
                                } else if (birinciGecis) {
                                                                                      // ELİT UYELER kısmını geçtikten sonra gelen satırlarda e-postaları yazdır
                                    String[] bigiDizisi = emailBilgi.split(" ");
                                    String email = bigiDizisi[2];
                                    araBellek[n] = email;
                                    System.out.println(araBellek[n]);
                                    n++;
                                    // System.out.println(email);
                                    araBellek[n]=email;
                                    System.out.println(araBellek[n]);
                                    n++;
                                    //emailsender emailSender= new emailsender();// alttaki kodlarda olduğu gibi bu satırda mail gönderme classına gönderiyor
                                   // emailSender.main(araBellek );
                                }
                            }
                        }

                        case 2 -> {
                            while ((emailBilgi = geciciOkuyucu.readLine()) != null) {
                                if (!ikinciGecis && emailBilgi.contains("GENEL UYELER")) {
                                    // GENEL UYELER kısmını geçtik
                                    ikinciGecis = true;
                                }
                                else if (ikinciGecis) {
                                    // GENEL UYELER kısmını geçtikten sonra gelen satırlarda e-postaları yazdır
                                    String[] bilgiDizisi = emailBilgi.split(" ");
                                    String email = bilgiDizisi[2];
                                    araBellek[n] = email;
                                    System.out.println(araBellek[n]);
                                    n++;
                                    // System.out.println(email);
                                    araBellek[n]=email;
                                    System.out.println(araBellek[n]);
                                    n++;
                                    //emailsender emailSender= new emailsender();
                                    //emailSender.main(araBellek);
                                }
                            }
                        }
                        case 3 -> {


                            while ((emailBilgi = geciciOkuyucu.readLine()) != null) {
                                if (!birinciGecis && emailBilgi.contains("ELİT UYELER")) {
                                    // ELİT UYELER kısmını geçtik
                                    birinciGecis = true;
                                } else if (!ikinciGecis && emailBilgi.contains("GENEL UYELER")) {
                                    // GENEL UYELER kısmını geçtik
                                    ikinciGecis = true;
                                } else if (birinciGecis && !ikinciGecis) {
                                    // ELİT UYELER kısmını geçtikten sonra gelen satırlarda e-postaları yazdır
                                    String[] parcalar = emailBilgi.split(" ");
                                    String email = parcalar[2];
                                    System.out.println(email);
                                } else if (birinciGecis && ikinciGecis) {
                                    // GENEL UYELER kısmını geçtikten sonra gelen satırlarda e-postaları yazdır
                                    String[] bilgiDizisi = emailBilgi.split(" ");
                                    String email = bilgiDizisi[2];
                                    araBellek[n] = email;
                                    System.out.println(araBellek[n]);
                                    n++;
                                    araBellek[n]=email;
                                    System.out.println(araBellek[n]);
                                    n++;
                                    //emailsender emailSender= new emailsender();
                                   // emailSender.main(araBellek);
                                    // System.out.println(email);
                                } else {
                                    // ELİT UYELER veya GENEL UYELER kısmına henüz gelmedik, UYELER yazdır
                                }
                            }
                        }
                        default -> System.out.println("Gecerli bir secim yapmadiniz.");
                    }
                }
            }}






