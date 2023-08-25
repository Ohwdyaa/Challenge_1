package org.binar;

import java.io.FileWriter; // library yang digunakan untuk menulis struk pembayaran ke dalam file
import java.io.IOException; // library ini digunakan untuk menangani potensi kesalahan yang mungkin terjadi saat bekerja dengan operasi input-output seperti menulis atau membaca file.
import java.io.PrintWriter; // library untuk cetak struk
import java.util.Scanner; // library ini digunakan untuk membaca input dari pengguna melalui konsol.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //membuat objek baru untuk membaca input dari pengguna
        int order; // variable penampung jumlah pesanan pengguna
        int totalHarga = 0; // variable penampung total pesanan pengguna
        StringBuilder pesananStr = new StringBuilder(); //Berfungsi untuk menyimpan data pesanan dalam bentuk teks

        System.out.println("Aplikasi Pemesanan Makanan");
        System.out.println("==========================");

        do { // menggunakan perulangan do untuk memastikan bahwa pengguna dapat
            // melakukan beberapa pilihan pesanan sebelum mereka memutuskan untuk keluar dari aplikasi.
            System.out.println("Menu :");
            System.out.println("1. Nasi Goreng      | 15.000");
            System.out.println("2. Mie Goreng       | 13.000");
            System.out.println("3. Nasi + Ayam      | 18.000");
            System.out.println("4. Es   Teh Manis   | 3.000");
            System.out.println("5. Es   Jeruk       | 5.000");
            System.out.println("0. Keluar dari aplikasi");
            System.out.print("Pilihan Anda: ");
            order = scanner.nextInt(); // untuk membaca inputan angka dari pengguna

            if (order >= 1 && order <= 5) {
                System.out.print("Masukkan jumlah pesanan: ");
                int qty = scanner.nextInt();
                int harga = 0;

                switch (order) {
                    case 1:
                        harga = 15000;
                        pesananStr.append(qty).append("x Nasi Goreng\n");
                        break;
                    case 2:
                        harga = 13000;
                        pesananStr.append(qty).append("x Mie Goreng\n");
                        break;
                    case 3:
                        harga = 18000;
                        pesananStr.append(qty).append("x Nasi + Ayam\n");
                        break;
                    case 4:
                        harga = 3000;
                        pesananStr.append(qty).append("x Es Teh Manis\n");
                        break;
                    case 5:
                        harga = 5000;
                        pesananStr.append(qty).append("x Es Jeruk\n");
                        break;
                }

                totalHarga += qty * harga;
            } else if (order == 0) {
                System.out.println("Keluar dari aplikasi.");
            } else {
                System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        } while (order != 0);

        if (totalHarga > 0) {
            konfirmasiDanPembayaran(pesananStr.toString(), totalHarga);
        }

        scanner.close();
    }

    public static void konfirmasiDanPembayaran(String pesanan, int totalHarga) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nKonfirmasi dan Pembayaran");
        System.out.println("=========================");
        System.out.println("Detail Pesanan:");
        System.out.println(pesanan);
        System.out.println("Total Harga: " + totalHarga);
        System.out.println("\n1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke Menu Utama");
        System.out.print("Pilihan Anda: ");
        int order = scanner.nextInt();

        // porgram mencetak 2 kondisi digunakanlah percabangan if
        if (order == 1) {
            String struk = "Struk Pembayaran:\n" + pesanan + "Total Harga: " + totalHarga;
            simpanStrukKeFile(struk);
            System.out.println("Pesanan berhasil dikonfirmasi dan struk pembayaran tersimpan.");
        } else if (order == 2) {
            System.out.println("Kembali ke Menu Utama.");
        } else {
            System.out.println("Pilihan tidak valid.");
        }

        scanner.close();
    }


    public static void simpanStrukKeFile(String struk) {
        try {
            FileWriter fileWriter = new FileWriter("struk_pembayaran.txt"); //membuat objek FileWriter dengan memberikan nama file "struk_pembayaran.txt" sebagai argumen.
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(struk); // menggunakan metode println untuk menulis string struk (yang berisi informasi struk pembayaran) ke dalam file.
            printWriter.close(); // menutup objek PrintWriter
        } catch (IOException e) { // kode ini akan dijalankan jika pengecualian IOException terjadi akan mencetak pesan kesalahan1

            System.out.println("Terjadi kesalahan saat menyimpan struk.");
        }
    } // Dengan blok try-catch, program memiliki mekanisme untuk menangkap pengecualian yang terjadi, mengatasi kesalahan, dan melanjutkan eksekusi program dengan cara yang terkontrol.
}
