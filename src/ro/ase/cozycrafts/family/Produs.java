package ro.ase.cozycrafts.family;

import ro.ase.cozycrafts.exception.InvalidProductDataException;

import java.io.Serializable;

/**
 * Clasa abstracta folosita pt crearea produselor
 */


public abstract class Produs implements IPromotie, Comparable<Produs>, Serializable {
    /** Versiunea clasei pentru serializare (compatibilitatea fisierelor binare salvate). */
    private static final long serialVersionUID=1L;

    protected String id;
    protected String denumire;
    protected CategorieProdus categorie;
    protected double pret;
    protected int stoc;
    protected boolean areReducereActiva;

    /**
     * constructor cu validari
     *
     * @param id                 identificator unic al produsului
     * @param denumire           denumirea produsului
     * @param categorie          categoria produsului
     * @param pret
     * @param stoc
     * @param areReducereActiva
     * @throws InvalidProductDataException
     */
    public Produs(String id, String denumire, CategorieProdus categorie, double pret, int stoc,
                  boolean areReducereActiva) throws InvalidProductDataException {
        this.id = verificaText(id, "ID");
        this.denumire = verificaText(denumire, "denumire");

        if(categorie==null){
            throw new InvalidProductDataException("Categoria produsului lipseste");
        }

        if(pret<=0){
            throw new InvalidProductDataException("Pretul nu poate fi mai mic sau egal cu 0");
        }

        if(stoc<0){
            throw new InvalidProductDataException("Stocul nu poate fi negativ.");
        }

        this.categorie = categorie;
        this.pret = pret;
        this.stoc = stoc;
        this.areReducereActiva = areReducereActiva;
    }

    /**
     * Verifica un camp de tip text sa nu fie gol si sa nu contina delimitatorul de text
     * @param valoare text verificat
     * @param numeCamp
     * @return textull fara spatii
     * @throws InvalidProductDataException dc textul este gol sau contine ;
     */
    protected static String verificaText(String valoare, String numeCamp)throws
            InvalidProductDataException {
        if(valoare==null||valoare.trim().isEmpty()){
            throw new InvalidProductDataException("Campul '"+numeCamp+"' nu poate fi gol.");
        }

        if(valoare.contains(";")){
            throw new InvalidProductDataException("Campul '"+numeCamp+"' nu poate contine caracterul ';'.");
        }

        return valoare.trim();
    }

    /**
     * @return tipul produsului scris ca prim camp in fisier (HANDMADE sau KIT)
     */
    public abstract String getTipProdus();

    /**
     * @return reprezentarea produsului ca linie de fisier text,
     * cu campurile separate prin ';'.
     */
    public abstract String toCsvFormat();

    /**
     * @return valoarea totala a stocului
     */
    public double calculeazaValoareTotalaStoc(){
        return this.pret*this.stoc;
    }

    @Override
    public void aplicaReducere(double procent) throws InvalidProductDataException{
        if(areReducereActiva){
            throw new InvalidProductDataException("Produsul are deja o reducere activa.");
        }

        if(procent<=0 || procent>50){
            throw new InvalidProductDataException("Procentul de reducere trebuie sa fie intre 1% si 50%.");
        }

        double pretNou=this.pret*(1-procent/100.0);
        this.pret=Math.round(pretNou*100)/100.0;//rotunjire la 2 zecimale
        this.areReducereActiva=true;
    }

    @Override
    public boolean areReducere() {
        return areReducereActiva;
    }

    /**
     *compara doua produse dupa pret, in ordine descrescatoare
     */
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

    /**
     *
     * @param denumire noua
     * @throws InvalidProductDataException dc denumirea e goala sau contine ;
     */
    public void setDenumire(String denumire) throws InvalidProductDataException{
        this.denumire = denumire;
    }

    /**
     * @param pret nou
     * @throws InvalidProductDataException dc pretul<=0
     */
    public void setPret(double pret) throws InvalidProductDataException{
        if(pret<=0){
            throw new InvalidProductDataException("Pretul modificat trebuie sa fie pozitiv si diferit de 0");
        }
        this.pret = pret;
    }

    /**
     * @param stoc nou
     * @throws InvalidProductDataException dc stocul este negativ
     */
    public void setStoc(int stoc) throws InvalidProductDataException{
        if(stoc<0){
            throw new InvalidProductDataException("Stocul modificat trebuie sa fie pozitiv");
        }
        this.stoc = stoc;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ID: ").append(id).append("] ");
        sb.append(denumire);
        sb.append(" | Categorie: ").append(categorie.getEticheta());
        sb.append(" | Pret: ").append(String.format("%.2f", pret)).append(" RON");
        if (areReducereActiva) {
            sb.append(" (redus)");
        }
        sb.append(" | Stoc: ").append(stoc).append(" buc.");
        return sb.toString();
    }
}
