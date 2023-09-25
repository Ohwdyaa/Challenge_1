package org.binar;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static String [] model = {"Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh", "Es Jeruk"};
    public static int [] price = {15000, 13000, 18000, 3000, 5000};
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("selamat datang di binar food");
        System.out.println("============================");
        ShowMenu();
        int choice = order();
        int quantity = getQuantity();
        int totalPrice = calculate(choice, quantity);

        double paymentAmount = getPaymentAmount(input);
        if (paymentAmount < totalPrice) {
            System.out.println("Jumlah pembayaran tidak mencukupi. Silakan bayar sesuai total harga.");
            return;
        }

        double change = calculateChange(paymentAmount, totalPrice);
        System.out.println("Kembalian: Rp " + change);

        displayReceipt(choice, quantity, totalPrice, paymentAmount, change);

        saveReceiptAsText(choice, quantity, totalPrice, paymentAmount, change);
        System.out.println("Terima kasih telah memesan. Selamat menikmati!");
        input.close();
    }

    public static void ShowMenu(){
        for (int i = 0; i < model.length; i++) {
            String menu = model[i];
            int prices = price[i];
            System.out.println((i + 1) + ". " + menu + " | " + prices);
        }
    }

    public static int order(){
        System.out.println("Pilih menu :");
        int choice = input.nextInt();
        if (choice < 1 || choice > model.length){
            System.out.println("Pilihan yang anda masukan tidak valid, pilih kembali dengan memasukan pilihan yang benar");
            return order();
        }
        return choice;
    }

    public static int getQuantity(){
        System.out.println("Jumlah yang ingin dipesan :");
        int quantity = input.nextInt();
        if (quantity <= 0){
            System.out.println("Jumlah yang dimasukan tidak valid");
            return getQuantity();
        }
        return quantity;
    }
    public static int calculate(int choice, int quantity){
        int selectedIndex = choice - 1;
        int selectedPrice = price[selectedIndex];
        int totalPrice = selectedIndex + selectedPrice;
        System.out.println("Total harga : Rp" + totalPrice);
        return totalPrice;
    }

    public static double getPaymentAmount(Scanner scanner) {
        System.out.print("Masukkan jumlah pembayaran: Rp ");
        return scanner.nextDouble();
    }

    public static double calculateChange(double paymentAmount, double totalPrice) {
        return paymentAmount - totalPrice;
    }
    public static void displayReceipt(int choice, int quantity, int totalPrice, double paymentAmount, double change) {
        System.out.println("\nIni adalah struk pembayaran:");
        System.out.println("Pesanan: " + model[choice - 1] + " x" + quantity);
        System.out.println("Total Harga: Rp " + totalPrice);
        System.out.println("Pembayaran: Rp " + paymentAmount);
        System.out.println("Kembalian: Rp " + change);
    }

    public static void saveReceiptAsText(int choice, int quantity, int totalPrice, double paymentAmount, double change) {
        try {
            PrintWriter writer = new PrintWriter("struk_pembayaran.txt");
            writer.println("Ini adalah struk pembayaran:");
            writer.println("Pesanan: " + model[choice - 1] + " x" + quantity);
            writer.println("Total Harga: Rp " + totalPrice);
            writer.println("Pembayaran: Rp " + paymentAmount);
            writer.println("Kembalian: Rp " + change);
            writer.close();
            System.out.println("Struk pembayaran telah disimpan sebagai struk_pembayaran.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Gagal menyimpan struk pembayaran.");
            e.printStackTrace();
        }
    }
}