package ro.ase.cozycrafts.family;

import ro.ase.cozycrafts.exception.InvalidProductDataException;

import java.io.Serializable;

public abstract class Produs implements IPromotie, Comparable<Produs>, Serializable {
    //pentru a nu exista erori la deserializarea fisierului binar
    private static final long serialVersionUID=1L;

    protected String id;
    protected String denumire;
    protected CategorieProdus categorie;
    protected double pret;
    protected int stoc;
    protected boolean areReducereActiva;

    //Constructor cu validari
    public Produs(String id, String denumire, CategorieProdus categorie, double pret, int stoc, boolean areReducereActiva)
    throws InvalidProductDataException {
        if(id==null || id.trim().isEmpty()){
            throw new InvalidProductDataException("ID-ul produsului este gol.");
        }

        if(denumire==null || denumire.trim().isEmpty()){
            throw new InvalidProductDataException("Lipseste denumirea produsului.");
        }

        if(pret<=0){
            throw new InvalidProductDataException("Pretul nu poate fi mai mic sau egal cu 0");
        }

        if(stoc<0){
            throw new InvalidProductDataException("Stocul nu poate fi negativ.");
        }
        this.id = id.trim();
        this.denumire = denumire.trim();
        this.categorie = categorie;
        this.pret = pret;
        this.stoc = stoc;
        this.areReducereActiva = false;
    }

    //metode abstracte ce vor fi mostenite de clasele derivate
    public abstract String getTipProdus();
    public abstract String toCsvFormat();

    //metoda folosita pt a calcula valoarea stocului
    public double calculeazaValoareTotalaStoc(){
        return this.pret*this.stoc;
    }

    //implementare metode din interfata IPromotie
    @Override
    public void aplicaReducere(double procent) throws InvalidProductDataException{
        if(procent<=0 || procent>50){
            throw new InvalidProductDataException("Procentul de reducere trebuie sa fie intre 1% si 50%.");
        }

        this.pret=this.pret*(1-procent/100.0);
        this.areReducereActiva=true;
    }

    @Override
    public boolean areReducere() {
        return false;
    }

    //implementare interfata Comparable pt a sorta descrescator in functie de pret
    @Override
    public int compareTo(Produs o) {
        if(this.pret<o.pret){
            return -1;
        }else if(this.pret>o.pret){
            return 1;
        }
        return 0;
    }

    public String getId() {
        return id;
    }

    public String getDenumire() {
        return denumire;
    }

    public CategorieProdus getCategorie() {
        return categorie;
    }

    public double getPret() {
        return pret;
    }

    public int getStoc() {
        return stoc;
    }

    public void setPret(double pret) throws InvalidProductDataException{
        if(pret<=0){
            throw new InvalidProductDataException("Pretul modificat trebuie sa fie pozitiv si diferit de 0");
        }
        this.pret = pret;
    }

    public void setStoc(int stoc) throws InvalidProductDataException{
        if(stoc<0){
            throw new InvalidProductDataException("Stocul modificat trebuie sa fie pozitiv");
        }
        this.stoc = stoc;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[ID: ").append(id).append("] ");
        sb.append(denumire);
        sb.append(" | Categorie: ").append(categorie != null ? categorie.getEticheta() : "Nespecificat");
        sb.append(" | Pret: ").append(String.format("%.2f", pret)).append(" RON");
        sb.append(" | Stoc: ").append(stoc).append(" buc.");
        return sb.toString();
    }
}
