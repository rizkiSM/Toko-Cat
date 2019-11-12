package tokocat;

import Toko.JenisKemasan;
import Toko.Warna;
import java.io.IOException;

/*
 * author Airyu
 */
public class Cat {
    private String merk, jenis, kodeWarna, warna, kemasan;
    private int stok, harga;
    
    public Cat(String merk, String jenis, String kodeWarna, 
            String kemasan, int stok, int harga){
        this.merk = merk;
        this.jenis = jenis;
        this.kodeWarna = kodeWarna;
        this.kemasan = kemasan;
        this.stok = stok;
        this.harga = harga;
        
        try {
            this.warna = Warna.cariWarna(kodeWarna);
        } catch(IOException e){
            System.err.println(e);
        }
    }
    
    public Cat(String record){      //jika input vector
        // ctL_MerkCat_kodeWarna#stok#harga
        String[] data = record.split("#"); //dari record (4 bagian)
        String[] PK = data[0].split("_");  //dari PK (3 bagian)
        
        this.stok = Integer.parseInt(data[2]);
        this.harga = Integer.parseInt(data[3]);
        this.merk = data[1];
        this.kodeWarna = PK[2];
        
        try {
            this.warna = Warna.cariWarna(kodeWarna);
        } catch(IOException e){
            System.err.println(e);
        }
        this.kemasan = JenisKemasan.cariKemasan(PK[0].substring(2));
        this.jenis = JenisKemasan.cariJenisCat(PK[0].substring(0, 2));
    }

    public String getMerk() {
        return merk;
    }
    
    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKodeWarna() {
        return kodeWarna;
    }

    public void setKodeWarna(String kodeWarna) {
        this.kodeWarna = kodeWarna;
    }

    public String getKemasan() {
        return kemasan;
    }
    
    public String getWarna() {
        return warna;
    }

    public void setWarna(String kodeWarna) throws IOException{
        kodeWarna = Warna.cariWarna(kodeWarna);
        this.warna = kodeWarna;
    }

    public void setKemasan(String kemasan) {
        this.kemasan = kemasan;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
    
    public String getRecord(){
        String record;
        
        // buat Primary Key
        record= JenisKemasan.getPK(jenis, kemasan);
        record += "_" + merk.replace(" ", "");
        record += "_" + kodeWarna;
        
        //lanjutkan buat record
        record += "#" + merk;
        record += "#" + stok;
        record += "#" + harga;
        
        return record;
    }
}
