package ro.ase.cozycrafts.service;

import ro.ase.cozycrafts.family.Produs;
import ro.ase.cozycrafts.storage.StorageStrategy;
import ro.ase.cozycrafts.storage.TextFileStorageStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * clasa singleton care gestioneaza in memorie starea magazinului online
 * produsele sunt memorate in doua colectii iar vanzarile in doua masive
 * context pentru patternul strategy
 * toate modificarile se fac in memorie, salvarea in fisiere se face la inchiderea aplicatiei
 */
public class MagazinService {
    public static final String fisierProduse="data/produse.txt";
    public static final String fisierVanzari="data/vanzari.txt";
    public static final String fisierRaport="data/raport_magazin.txt";

    private static MagazinService instance=null;

    private StorageStrategy storageStrategy;

    private List<Produs> listaProduse;
    private Map<String, Produs> mapProduse;

    private double[] vanzariLunare;
    private int[][] bucatiVandute;

    public MagazinService() {
        this.storageStrategy = new TextFileStorageStrategy();
        this.listaProduse = new ArrayList<>();
        this.mapProduse = new HashMap<>();
        this.vanzariLunare = new double[12];
        this.bucatiVandute = new int[3][4];
    }

    public static synchronized MagazinService getInstance(){
        if(instance==null){
            instance=new MagazinService();
        }

        return instance;
    }

    //permite schimbarea modului de salvare la runtime
    public void setStorageStrategy(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public void salveazaDateInFisier(String caleFisier)throws Exception{
        this.storageStrategy.salveazaProduse(caleFisier, this.listaProduse);
    }

    public void incarcaDateDinFisier(String caleFisier) throws Exception{
        this.listaProduse=this.storageStrategy.incarcaProduse(caleFisier);
    }
}
