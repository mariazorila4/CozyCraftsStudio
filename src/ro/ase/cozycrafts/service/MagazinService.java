package ro.ase.cozycrafts.service;

import ro.ase.cozycrafts.family.Produs;
import ro.ase.cozycrafts.storage.StorageStrategy;
import ro.ase.cozycrafts.storage.TextFileStorageStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MagazinService {
    private static MagazinService instance;

    private StorageStrategy storageStrategy;

    private List<Produs> listaProduse;
    private Map<String, Produs> mapProduse;

    private double[] vanzariLunare;
    private int[][] distributieStocDepozit;

    public MagazinService() {
        this.storageStrategy = new TextFileStorageStrategy();
        this.listaProduse = new ArrayList<>();
        this.mapProduse = new HashMap<>();
        this.vanzariLunare = new double[12];
        this.distributieStocDepozit = new int[3][4];
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
        this.storageStrategy.salveazaProdus(caleFisier, this.listaProduse);
    }

    public void incarcaDateDinFisier(String caleFisier) throws Exception{
        this.listaProduse=this.storageStrategy.incarcaProduse(caleFisier);
    }
}
