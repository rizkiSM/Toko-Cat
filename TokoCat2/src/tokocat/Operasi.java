package tokocat;

import Toko.FileIO;
import Toko.Input;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Airyu
 */
public class Operasi {
    public static String menu(){
        Scanner inputUser = new Scanner(System.in);
        
        System.out.println(" = MENU =\n"
                + "==========\n"
                + "1. Menampilkan data\n"
                + "2. Menambah data barang\n"
                + "3. Mengubah data barang\n"
                + "4. Menghapus data barang\n"
                + "5. Mendata barang masuk\n"
                + "6. Mengecek stok barang\n"
                + "7. Mendata penjualan\n"
                + "8. Menampilkan riwayat penjualan\n"
                + "9. Melihat rank barang\n"
                + "Lainnya untuk keluar.\n"
                + "--------------------------------");
        System.out.print("Pilihan anda : ");
        return inputUser.next();
    }
    public static  void tampilkanData() throws IOException{
        System.out.println("Daftar cat :");
        System.out.println("+----+----------------+-------------+------------+----------------+--------------+------+-----------+");
        System.out.println("| no |    Merk Cat    |  Jenis Cat  | Kode Warna |     Warna      | Kemasan (gr) | Stok |   Harga   |");
        System.out.println("+----+----------------+-------------+------------+----------------+--------------+------+-----------+");
        
        Vector<String> record = FileIO.bacaFile("D:/DBcat.txt");
        for (int i = 0; i < record.size(); i++) {
            Cat cat = new Cat(record.elementAt(i));
            
            System.out.printf("| %-2d | %-14s |", (i + 1), cat.getMerk());
            System.out.printf(" %-11s |", cat.getJenis());
            System.out.printf(" %-10s |", cat.getKodeWarna());
            System.out.printf(" %-14s |", cat.getWarna());
            System.out.printf(" %-12s |", cat.getKemasan());
            System.out.printf(" %-4d |", cat.getStok());
            System.out.printf(" %-9d |\n", cat.getHarga());
        }
        System.out.println("+----+----------------+-------------+------------+----------------+--------------+------+-----------+");
    }
    public static  void tampilkanData(Cat cat) throws IOException{
        System.out.println("---------------------------------------------");
        System.out.println("Merk\t\t : "+ cat.getMerk());
        System.out.println("Jenis\t\t : "+ cat.getJenis());
        System.out.println("Kode warna\t : "+ cat.getKodeWarna());
        System.out.println("Warna\t\t : "+ cat.getWarna());
        System.out.println("Kemasan\t\t : "+ cat.getKemasan());
        System.out.println("Stok\t\t : "+ cat.getStok());
        System.out.println("Harga\t\t : "+ cat.getHarga());
        System.out.println("---------------------------------------------");
    }
    public static  void tambahBarang() throws IOException{
        Scanner inputUser = new Scanner(System.in);
        String merk, jenis, kodeWarna, warna, kemasan;
        int stok, harga;
        
        System.out.println("\n = Tambah Data =");
        System.out.println("=================");
        
        // input data 
        merk = Input.merk();
        jenis = Input.jenisCat();
        kodeWarna = Input.kodeWarna();
        kemasan = Input.kemasan();
        stok = Input.stok();
        harga = Input.harga();
        
        System.out.println("\nData yang anda inputkan :");
        System.out.println(
                "Merk\t\t : "+merk+"\n"+
                "Jenis cat\t : "+jenis+"\n"+
                "Kode warna\t : "+kodeWarna+"\n"+
                "Kemasan\t\t : "+kemasan+"gr"+"\n"+
                "Stok\t\t : "+stok+"\n"+
                "Harga\t\t : "+harga);
        
        if (getYesorNo("Apakah anda ingin menambahkan data tersebut")) {
            Cat cat = new Cat(merk, jenis, kodeWarna, kemasan, stok, harga);
            if (!FileIO.cekDatadiDB(cat.getRecord()) ){
                FileIO.tulisFile(cat.getRecord(), "D:/DBcat.txt");
            }
            System.out.println("Data barang berhasil di tambahkan.");
        }
    }
    public static  void ubahData() throws IOException{
        System.out.println("\n = Ubah Data =");
        System.out.println("================\n");
        tampilkanData();
        
        // baca file
        Vector<String> record = FileIO.bacaFile("D:/DBcat.txt");
        Scanner inputUser = new Scanner(System.in);
        
        // tanya nomor data yang akan di update
        System.out.print("Masukkan nomor cat yang akan diupdate : "); 
        int nomorData = inputUser.nextInt();
        
        if (nomorData > record.size()) {  // jika data yang di hapus tidak ada
            System.out.println("\nData tidak ditemukan !!");
        }else{
            // update setiap atribut cat
            Cat cat = new Cat(record.elementAt(nomorData-1));
            if (getYesorNo("Ubah merk?")) cat.setMerk(Input.merk());
            if (getYesorNo("Ubah jenis cat?")) cat.setJenis(Input.jenisCat());
            if (getYesorNo("Ubah kode warna?")) cat.setKodeWarna(Input.kodeWarna());
            if (getYesorNo("Ubah kemasan?")) cat.setStok(Input.stok());
            if (getYesorNo("Ubah stok?")) cat.setStok(Input.stok());
            if (getYesorNo("Ubah harga?")) cat.setHarga(Input.harga());

            // tampilkan data sebelum di update
            System.out.println("\nData yang akan diupdate :");
            tampilkanData(cat);
            
            if (getYesorNo("Apakah anda ingin mengupdate data tersebut?")) {
                // tulis database baru
                FileIO.hapusDataDatabase("D:/DBcat.txt");
                record.setElementAt(cat.getRecord(), nomorData-1);
                for (int i = 0; i < record.size(); i++) {
                    FileIO.tulisFile(record.elementAt(i), "D:/DBcat.txt");
                }
                
                System.out.println("\nData berhasil diupdate");
            }
        }
    }
    public static  void hapusData() throws IOException{
        System.out.println("\n = Hapus Data = ");
        System.out.println("================");
        tampilkanData();
        
        // baca file
        Vector<String> record = FileIO.bacaFile("D:/DBcat.txt");
        Scanner inputUser = new Scanner(System.in);
        
        // tanya data yang akan dihapus 
        System.out.print("Masukkan nomor cat yang akan dihapus : ");
        int nomorData = inputUser.nextInt();
        if (nomorData > record.size()) {  // jika data yang di hapus tidak ada
            System.out.println("\nData tidak ditemukan !!");
        }else{
            // tampilkan data sebelum di hapus
            Cat cat = new Cat(record.elementAt(nomorData-1));
            System.out.println("\nData yang akan dihapus :");
            tampilkanData(cat);
            
            if (getYesorNo("Apakah anda ingin menghapus data tersebut?")) {
                // hapus data di database
                FileIO.hapusDataDatabase("D:/DBcat.txt");
                
                // tulis database baru
                record.remove(nomorData-1);
                for (int i = 0; i < record.size(); i++) {
                    FileIO.tulisFile(record.elementAt(i), "D:/DBcat.txt");
                }
            }
        }
        
    }
    public static  void mendataBarangMasuk()throws IOException{ // belum sekesai
        System.out.println("\n = Barang Masuk = ");
        System.out.println("==================");
        tampilkanData();
        
        Vector<String> record = FileIO.bacaFile("D:/DBcat.txt");
        Scanner inputUser = new Scanner(System.in);
        
        boolean adaBarangMasuk = false;
        do {
            System.out.print("Masukkan nomor data : ");
            int nomorData = inputUser.nextInt();
            int barangMasuk;
            if (nomorData > record.size()) {
                System.out.println("Data tidak ditemukan !!");
            }else {
                adaBarangMasuk = true;
                Cat cat = new Cat(record.elementAt(nomorData-1));
                tampilkanData(cat);
                System.out.print("Barang masuk = ");
                barangMasuk = inputUser.nextInt() + cat.getStok();
                cat.setStok(barangMasuk);
                record.setElementAt(cat.getRecord(), nomorData-1);
            }
        }while (getYesorNo("\nApakah anda ingin melanjutkan mendata barang masuk?"));
        
        // hapus dan ganti database jika ada barang masuk
        if (adaBarangMasuk) {
            FileIO.hapusDataDatabase("D:/DBcat.txt");
            for (int i = 0; i < record.size(); i++) {
                FileIO.tulisFile(record.elementAt(i), "D:/DBcat.txt");
            }
            System.out.println("Data berhasil diproses.");
        }
    }
    public static  void cekStok() throws IOException{
        System.out.println("\n = Cek Stok Barang = ");
        System.out.println("======================");
        
        Vector<String> record = FileIO.bacaFile("D:/DBcat.txt");
        Scanner inputUser = new Scanner(System.in);
        String[] keywords;
        
        System.out.print("Masukkan keyword : "); 
        keywords = inputUser.nextLine().split(" ");
        System.out.println("+----------------+-------------+---------+----------------+------+");
        System.out.println("|      Merk      |  Jenis Cat  | Kemasan |     Warna      | Stok |");
        System.out.println("+----------------+-------------+---------+----------------+------+");
        for (int i = 0; i < record.size(); i++) {
            boolean adaData = true;
            for (String keyword : keywords){
                adaData = adaData && (record.elementAt(i).toLowerCase()).contains(keyword.toLowerCase() );
            }
            
            if (adaData) {
                Cat cat = new Cat(record.elementAt(i));
                System.out.printf("| %-14s |", cat.getMerk());
                System.out.printf(" %-11s |", cat.getJenis());
                System.out.printf(" %-7s |", cat.getKemasan());
                System.out.printf(" %-14s |", cat.getWarna());
                System.out.printf(" %-4d |\n", cat.getStok());
            }
        }
        System.out.println("+----------------+-------------+---------+----------------+------+");
    }
    public static  void mendataPenjualan() throws IOException{
        System.out.println("\n = Mendata Penjualan =");
        System.out.println("========================\n");
        tampilkanData();
        
        // baca file
        Vector<String> record = FileIO.bacaFile("D:/DBcat.txt");
        Scanner inputUser = new Scanner(System.in);
        
        // tanya nomor data yang akan di update
        System.out.print("Masukkan nomor cat terjual : "); 
        int nomorData = inputUser.nextInt();
        String tanggal;
        int terjual;
        
        if (nomorData > record.size()) {  // jika data tidak ditemukan
            System.out.println("\nData tidak ditemukan !!");
        }else{
            // update setiap atribut cat
            Cat cat = new Cat(record.elementAt(nomorData-1));
            tanggal = ambilTanggal();
            System.out.print("Jumlah barang terjual : ");
            terjual = inputUser.nextInt();
            if (cat.getStok() < terjual) {
                System.out.println("Stok barang tersisa  : "+ cat.getStok());
            }else{
                cat.setStok(cat.getStok() - terjual);
            }

            // hapus database lama
            FileIO.hapusDataDatabase("D:/DBcat.txt");
            // tulis database baru
            record.setElementAt(cat.getRecord(), nomorData-1);
            for (int i = 0; i < record.size(); i++) {
                FileIO.tulisFile(record.elementAt(i), "D:/DBcat.txt");
            }
            
            // tulis database penjualan
            String[] PK = record.elementAt(nomorData-1).split("#");
            FileIO.tulisFile(PK[0]+"#"+tanggal+"#"+terjual, "D:/DBPenjualan.txt");
            
            System.out.println("\nData penjualan berhasil disimpan");
        }
    }
    public static  void tampilkankRiwayatPenjualan() throws IOException{
        System.out.println("\n = Riwayat Penjualan =");
        System.out.println("=======================");
        
        Vector<String> record = FileIO.bacaFile("D:/DBPenjualan.txt");
        
        System.out.println("+----+----------------------+------------+---------+");
        System.out.println("| no |         Kode         |  Tanggal   | Terjual |");
        System.out.println("+----+----------------------+------------+---------+");
        for (int i = 0; i < record.size(); i++) {
            String[] data = record.elementAt(i).split("#");
            System.out.printf("| %-2d | %-20s |", (i+1), data[0]);
            System.out.printf(" %-10s |", data[1]);
            System.out.printf("    %-4s |\n", data[2]);
        }
        System.out.println("+----+----------------------+------------+---------+");
    }
    public static  void lihatRankBarang() throws IOException{
        System.out.println("\n = Rank Barang =");
        System.out.println("=================\n");
        
        // baca semua barang
        Vector<String> recordCat = FileIO.bacaFile("D:/DBCat.txt");
        Cat cat;
        
        // buat semua stok menjadi 0. Stok digunakan untuk menghitung jumlah brang teerjual
        for (int i = 0; i < recordCat.size(); i++) {
            cat = new Cat(recordCat.elementAt(i));
            cat.setStok(0);
            recordCat.setElementAt(cat.getRecord(), i);
        }
        
        // baca data penjualan
        Vector<String> recordPenjualan = FileIO.bacaFile("D:/DBPenjualan.txt");
        
        // hitung total penjualan
        for (int i = 0; i < recordCat.size(); i++) {
            cat = new Cat(recordCat.elementAt(i));
            String[] data = recordCat.elementAt(i).split("#");
            String PKcat = data[0];
            int terjual = 0;
            
            // baca penjualan
            for (int j = 0; j < recordPenjualan.size(); j++) {
                if (recordPenjualan.elementAt(j).contains(PKcat)) {
                    String[] dataTerjual = recordPenjualan.elementAt(j).split("#");
                    terjual += Integer.parseInt(dataTerjual[2]);
                }
            }
            
            cat.setStok(terjual);
            recordCat.setElementAt(cat.getRecord(), i);
        }
        // urutkan data
        for (int i = 0; i < recordCat.size(); i++) {
            for (int j = 0; j < (recordCat.size())-i; j++) {
                cat = new Cat(recordCat.elementAt(i));                
                Cat cat2 = new Cat(recordCat.elementAt(j+i));
                if (cat.getStok() < cat2.getStok()) {
                    recordCat.setElementAt(cat2.getRecord(), i);
                    recordCat.setElementAt(cat.getRecord(), (i+j));
                }
            }
        }
        
        // tampilkan data
        System.out.println("Rank cat :");
        System.out.println("+----+----------------+-------------+----------------+---------+---------+");
        System.out.println("| no |    Merk Cat    |  Jenis Cat  |     Warna      | Kemasan | Terjual |");
        System.out.println("+----+----------------+-------------+----------------+---------+---------+");
        
        for (int i = 0; i < recordCat.size(); i++) {
            cat = new Cat(recordCat.elementAt(i));
            
            System.out.printf("| %-2d | %-14s |", (i + 1), cat.getMerk());
            System.out.printf(" %-11s |", cat.getJenis());
            System.out.printf(" %-14s |", cat.getWarna());
            System.out.printf(" %-7s |", cat.getKemasan());
            System.out.printf("   %-5d |\n", cat.getStok());
        }
        System.out.println("+----+----------------+-------------+----------------+---------+---------+");
    }
    
    public static boolean getYesorNo(String massage){
        Scanner inputUser = new Scanner(System.in);
        System.out.print(massage + " (y/n)? ");
        String pilihan = inputUser.nextLine();
        while (!pilihan.equalsIgnoreCase("y") && !pilihan.equalsIgnoreCase("n")){
            System.out.println("\nPilihan tidak tersedia, silahkan pilih y/n.");
            System.out.print(massage + " (y/n)? ");
            pilihan = inputUser.nextLine();
        }
        return pilihan.equalsIgnoreCase("y");
    }
    public static String ambilTanggal(){
        Scanner inputUser = new Scanner(System.in);
        String tanggal;
        boolean ulangi = true;
        
        do{
            System.out.print("Tanggal (yyyy-MM-dd) : ");
            tanggal = inputUser.next();
            try {
                ulangi = false;
                LocalDate.parse(tanggal);
            } catch (Exception e){
                ulangi = true;
                System.out.println("Format salah. Masukkan yyyy-MM-dd");
            }
        }while (ulangi);
        return tanggal;
    }
}
