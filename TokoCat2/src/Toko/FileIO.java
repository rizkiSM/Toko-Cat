package Toko;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author Airyu
 */
public class FileIO {
    public static Vector<String> bacaFile(String path){
        Vector<String> record = new Vector<>();
        
        try (
                FileReader fileInput = new FileReader(path);
                BufferedReader bufferedReader = new BufferedReader(fileInput);
                ){
            String data = bufferedReader.readLine();
            
            while(data != null){
                record.add(data);                
                data = bufferedReader.readLine();
            }
            
        } catch(IOException e) {
            System.out.println("File "+path+" tidak ditemukan, silahkan input terlebih dahulu");
        }
        return record;
    }
    
    public static void tulisFile(String record, String path){
        try (
                FileWriter fileOutput = new FileWriter(path, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileOutput);
                ){
            
            if (adaData(path)) { // cek sudah ada input atau belum
                bufferedWriter.newLine(); // jika sudah ada data, tambah new line
            }
            bufferedWriter.write(record);
            bufferedWriter.flush();      
            
        } catch(IOException e) {
            System.out.println("File "+path+" tidak ditemukan, silahkan buat terlebih dahulu");
        }
    }
    
    private static boolean adaData(String path) throws IOException{ 
        try (
                FileReader fileInput = new FileReader(path);
                BufferedReader bufferedReader = new BufferedReader(fileInput);
                ){
            if (bufferedReader.readLine() == null ){
                return false;
            }
        }
        return true;
    }
    
    public static boolean cekDatadiDB(String keyword) throws IOException{
        try (
                FileReader fileInput = new FileReader("D:/DBcat.txt");
                BufferedReader bufferedReader = new BufferedReader(fileInput);
                ){
            String data = bufferedReader.readLine();

            while(data != null){
                if (data.toLowerCase().contains(keyword.toLowerCase()) ) {
                    return true;
                }
                data = bufferedReader.readLine();
            }
        }
        return false;
    }
    
    public static void hapusDataDatabase(String path) throws IOException{
        try (
                FileWriter fileOutput = new FileWriter(path)
                ){
            fileOutput.write("");
        }
    }
}
