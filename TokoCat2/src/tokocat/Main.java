package tokocat;

import java.io.IOException;

/**
 *
 * @author Airyu
 */
public class Main {
    public static void main(String[] args) throws IOException{
        String pilihan;
        boolean lanjutkan = true;
       
        System.out.println("18103020126 / 2E / RizkiSaputroM\n");
        while (lanjutkan) {            
            pilihan = Operasi.menu();
            switch (pilihan) {
                case "1" : Operasi.tampilkanData(); break;
                case "2" : Operasi.tambahBarang(); break;
                case "3" : Operasi.ubahData(); break;
                case "4" : Operasi.hapusData(); break;
                case "5" : Operasi.mendataBarangMasuk(); break;
                case "6" : Operasi.cekStok(); break;
                case "7" : Operasi.mendataPenjualan(); break;
                case "8" : Operasi.tampilkankRiwayatPenjualan(); break;
                case "9" : Operasi.lihatRankBarang(); break;
                default: 
                    System.out.println("\n\n\t== Akhir dari program ==");
                    lanjutkan = false;
            }
            System.out.println("\n");
        }
    }
}

