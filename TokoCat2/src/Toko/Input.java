package Toko;

import java.io.IOException;
import java.util.Scanner;

public class Input {
    private static Scanner inputUser = new Scanner(System.in);
    
    public static String merk(){
        System.out.print("Merk\t\t : ");
        return inputUser.nextLine();
    }
    
    public static String jenisCat(){
        String jenis;
        System.out.print("Jenis/Kode Cat\t : "); jenis = inputUser.nextLine();
        jenis = JenisKemasan.cariJenisCat(jenis);
        while (jenis.equals("0")){
            JenisKemasan.cetakJenisCat();
            System.out.println("Input anda tidak tersedia");
            System.out.println("Silahkan pilih salah satu ukuran di atas.");
            System.out.print("Jenis/Kode Cat\t : "); jenis = inputUser.nextLine();
            jenis = JenisKemasan.cariJenisCat(jenis);
        }
        return jenis;
    }
    
    public static String kodeWarna() throws IOException{
        String kodeWarna, warna;
        System.out.print("Kode Warna\t : "); kodeWarna = inputUser.nextLine();
        warna = Warna.cariWarna(kodeWarna);
        while ( warna.equals("0") ){
            Warna.cetakWarna();
            System.out.println("Input anda tidak tersedia");
            System.out.println("Silahkan pilih salah satu kode warna di atas.");
            System.out.print("Kode Warna\t : ");
            kodeWarna = inputUser.nextLine();
            warna = Warna.cariWarna(kodeWarna);
        }
        return kodeWarna;
    }
        
    public static String kemasan(){
        String kemasan;
        System.out.print("Kemasan (gr)\t : "); kemasan = inputUser.nextLine();
        kemasan = JenisKemasan.cariKemasan(kemasan);
        while (kemasan.equals("0")){
            JenisKemasan.cetakKemasan();
            System.out.println("Input anda tidak tersedia");
            System.out.println("Silahkan pilih salah satu ukuran di atas.");
            System.out.print("Kemasan (gr)\t : "); kemasan = inputUser.nextLine();
            kemasan = JenisKemasan.cariKemasan(kemasan);
        }
        return kemasan;
    }
    
    public static int stok(){
        System.out.print("Stok\t\t : ");
        return inputUser.nextInt();
    }
        
    public static int harga(){
        System.out.print("Harga\t\t : "); 
        return inputUser.nextInt();
    }
}
