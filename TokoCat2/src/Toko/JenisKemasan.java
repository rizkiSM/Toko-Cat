package Toko;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JenisKemasan {
    private static String[] jenisCat = {
        "ct#Cat Tembok",
        "ck#Cat Kayu",
        "cb#Cat Besi",
        "cg#Cat Genteng",
        "cm#Cat Mobil"
    };
    private static String[] kemasan = {
        "S#250",
        "M#500",
        "L#1000",
        "U#5000",
        "X#25000"    
    };
    
    public static void cetakJenisCat(){
        System.out.println("+------+-------------+");
        System.out.println("| Kode |  Jenis Cat  |");
        System.out.println("+------+-------------+");
        for (String data : jenisCat){
            String[] atribut = data.split("#");
            System.out.printf("|  %-3s |", atribut[0]);
            System.out.printf(" %-11s |\n", atribut[1]);
        }
        System.out.println("+------+-------------+");
    }
    
    public static void cetakKemasan(){
        System.out.println("+-----------+");
        System.out.println("|  Kemasan  |");
        System.out.println("+-----------+");
        for (String data : kemasan){
            String[] atribut = data.split("#");
            System.out.printf("| %-6sgr  |\n", atribut[1]);
        }
        System.out.println("+-----------+");
    }
    
    public static String cariJenisCat(String keyword){
        for (String data : jenisCat){
            if (data.toLowerCase().contains(keyword.toLowerCase())){
                String[] jenis = data.split("#");
                return jenis[1];
            }
        }
        return "0";
    }
    
    public static String cariKemasan(String keyword){
        for (String data : kemasan){
            if (data.toLowerCase().contains(keyword.toLowerCase())){
                String[] kode = data.split("#");
                return kode[1];
            }
        }
        return "0";
    }
    
    public static String getPK(String JenisCat, String Kemasan){
        String[] atribut;
        for (String data : jenisCat){
            if (data.contains(JenisCat)){
                atribut = data.split("#");
                JenisCat = atribut[0]; break;
            }
        }
        for (String data : kemasan){
            if (data.contains(Kemasan)){
                atribut = data.split("#");
                Kemasan = atribut[0]; break;
            }
        }
        return JenisCat+Kemasan;
    }
}
