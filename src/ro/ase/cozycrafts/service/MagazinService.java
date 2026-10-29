package ro.ase.cozycrafts.service;

import ro.ase.cozycrafts.family.CategorieProdus;
import ro.ase.cozycrafts.family.Luna;
import ro.ase.cozycrafts.family.Produs;
import ro.ase.cozycrafts.storage.StorageStrategy;
import ro.ase.cozycrafts.storage.TextFileStorageStrategy;

import java.io.*;
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

    private MagazinService() {
        this.storageStrategy = new TextFileStorageStrategy();
        this.listaProduse = new ArrayList<>();
        this.mapProduse = new HashMap<>();
        this.vanzariLunare = new double[Luna.values().length];
        this.bucatiVandute = new int[Luna.values().length][CategorieProdus.values().length];
    }

    /**
     *
     * @return instanta unica a magazinului
     */
    public static synchronized MagazinService getInstance(){
        if(instance==null){
            instance=new MagazinService();
        }

        return instance;
    }

    /**
     * permite schimbarea modului de salvare la runtime
     * @param storageStrategy noua strategie de stocare
     */
    public void setStorageStrategy(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    /**
     * Incarca produsele si vanzarile din fisiere, se apeleaza la pornirea aplicatiei
     * @throws IOException dc fisierul de produse nu poate fi citit
     */
    public void incarcaDate() throws IOException{
        listaProduse.clear();
        mapProduse.clear();

        for(Produs p:storageStrategy.incarcaProduse(fisierProduse)){
            if(mapProduse.containsKey(p.getId())){
                System.out.println("Produsul cu ID-ul "+p.getId()+" apare de doua ori in fisier si a fost ignorat.");
            }else{
                listaProduse.add(p);
                mapProduse.put(p.getId(), p);
            }
        }
        incarcaVanzari();
    }

    /**
     * salveaza produsele si vanzarile din fisiere, se apeleaza la inchiderea aplicatiei
     * @throws IOException
     */
    public void salveazaDate() throws IOException{
        storageStrategy.salveazaProduse(fisierProduse, listaProduse);
        salveazaVanzari();
    }

    /**
     * scrie vectorul si matricea in fisierul de vanzari
     * prima linie contine cele 12 valori lunare
     */
    private void salveazaVanzari() throws IOException{
        File fisier=new File(fisierVanzari);

        if(fisier.getParentFile()!=null){
            fisier.getParentFile().mkdirs();
        }

        try(PrintStream ps=new PrintStream(fisier)){

            //linia 1 reprezinta vanzarile din fiecare luna
            for(int i=0; i< vanzariLunare.length; i++){
                ps.print(vanzariLunare[i]+";");
            }
            ps.println();

            //urmatoarele 12 linii vor reprezenta matricea de bucati de produse vandute
            for(int i=0;i< bucatiVandute.length; i++){
                for(int j=0; j<bucatiVandute[i].length; j++){
                    ps.print(bucatiVandute[i][j]+";");
                }
                ps.println();
            }
        }
    }

    /**
     * citeste vectorul si matricea din fisierul de vanzari
     */
    private void incarcaVanzari() throws IOException{
        vanzariLunare=new double[Luna.values().length];
        bucatiVandute=new int[Luna.values().length][CategorieProdus.values().length];

        File fisier=new File(fisierVanzari);
        if(!fisier.exists()){
            return;
        }

        try(FileInputStream fluxIn=new FileInputStream(fisier);
            InputStreamReader isr=new InputStreamReader(fluxIn);
            BufferedReader bufferIn=new BufferedReader(isr)){

            String[] valori=imparteLinie(bufferIn.readLine(), vanzariLunare.length);

            for (int i = 0; i < vanzariLunare.length; i++) {
                vanzariLunare[i] = Double.parseDouble(valori[i].trim());
                if (vanzariLunare[i] < 0) {
                    throw new IllegalArgumentException("valoare negativa a vanzarilor");
                }
            }

            for (int i = 0; i < bucatiVandute.length; i++) {
                valori = imparteLinie(bufferIn.readLine(), bucatiVandute[i].length);
                for (int j = 0; j < valori.length; j++) {
                    bucatiVandute[i][j] = Integer.parseInt(valori[j].trim());
                    if (bucatiVandute[i][j] < 0) {
                        throw new IllegalArgumentException("numar negativ de bucati");
                    }
                }
            }
        }catch(IllegalArgumentException e){
            System.out.println("Fisierul de vanzari este invalid ("+e.getMessage()+").");
            vanzariLunare=new double[Luna.values().length];
            bucatiVandute=new int[Luna.values().length][CategorieProdus.values().length];
        }
    }

    /**
     * imparte o linie dupa ';' si verifica numarul de valori
     * @param linie
     * @param nrValori
     * @return
     */
    private String[] imparteLinie(String linie, int nrValori) {
        if(linie==null){
            throw new IllegalArgumentException("fisier incomplet");
        }

        String[] valori=linie.split(";");
        if(valori.length!=nrValori){
            throw new IllegalArgumentException("se asteptau "+nrValori+" valori pe linie");
        }

        return valori;
    }
}
