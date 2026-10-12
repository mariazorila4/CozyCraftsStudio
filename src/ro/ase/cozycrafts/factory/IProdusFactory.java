package ro.ase.cozycrafts.factory;

import ro.ase.cozycrafts.exception.InvalidProductDataException;
import ro.ase.cozycrafts.family.CategorieProdus;
import ro.ase.cozycrafts.family.Produs;

public interface IProdusFactory {
    public Produs getProdus(String id, String denumire, CategorieProdus categorie, double pret, int stoc, boolean areReducereActiva, String tipFir, String culoare, int timpOreLucru) throws InvalidProductDataException;
    public Produs getProdus(String id, String denumire, CategorieProdus categorie, double pret, int stoc, boolean areReducereActiva, String nivelDificultate, boolean contineTutorialVideo, int nrPiese) throws InvalidProductDataException;
}
