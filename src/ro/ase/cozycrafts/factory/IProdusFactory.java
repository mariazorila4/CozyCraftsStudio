package ro.ase.cozycrafts.factory;

import ro.ase.cozycrafts.exception.InvalidProductDataException;
import ro.ase.cozycrafts.family.CategorieProdus;
import ro.ase.cozycrafts.family.Produs;

/**
 * interfata pt pattern-ul Factory folosit pt a defini metodele de creare a produselor
 */

public interface IProdusFactory {

    /**
     *creeaza un articol handmade
     * @return obiectul ArticolHandmade creat
     * @throws InvalidProductDataException daca datele sunt invalide
     */
    public Produs getProdus(String id, String denumire, CategorieProdus categorie, double pret, int stoc,
                            boolean areReducereActiva, String tipFir, String culoare, int timpOreLucru) throws InvalidProductDataException;

    /**
     *creeaza un kit artizanat
     * @return obiectul KitArtizanat creat
     * @throws InvalidProductDataException daca datele sunt invalide
     */
    public Produs getProdus(String id, String denumire, CategorieProdus categorie, double pret, int stoc, boolean areReducereActiva, String nivelDificultate, boolean contineTutorialVideo, int nrPiese) throws InvalidProductDataException;
}
