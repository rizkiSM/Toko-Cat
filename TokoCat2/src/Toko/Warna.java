package Toko;

import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author Airyu
 */
public class Warna {
    public static String cariWarna(String kodeWarna) throws IOException{
        String namaWarna = "0";
        
        Vector<String> record = FileIO.bacaFile("D:/warna.txt");
        for (int i = 0; i < record.size(); i++) {
            String[] data = record.elementAt(i).split("#");
            if (kodeWarna.equals(data[0]) ) {
                namaWarna = data[1];
                break;
            }
        }
        return namaWarna;
    }
    
    public static void cetakWarna() throws IOException{
        Vector<String> record = FileIO.bacaFile("D:/warna.txt");
        String[] data;
        
        System.out.println("+------------+-----------------+");
        System.out.println("| Kode Warna |      Warna      |");
        System.out.println("+------------+-----------------+");
        for (int i = 0; i < record.size(); i++) {
            data = record.elementAt(i).split("#");
            System.out.printf("| %-10s | %-15s |\n", data[0], data[1]);
        }
        System.out.println("+------------+-----------------+");
    }
}
