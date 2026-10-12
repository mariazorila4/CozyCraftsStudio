package ro.ase.cozycrafts.factory;

import ro.ase.cozycrafts.exception.InvalidProductDataException;
import ro.ase.cozycrafts.family.ArticolHandmade;
import ro.ase.cozycrafts.family.CategorieProdus;
import ro.ase.cozycrafts.family.KitArtizanat;
import ro.ase.cozycrafts.family.Produs;

public class ProdusFactory implements IProdusFactory {
    @Override
    public Produs getProdus(String id, String denumire, CategorieProdus categorie, double pret, int stoc, boolean areReducereActiva, String tipFir, String culoare, int timpOreLucru) throws InvalidProductDataException {
        if (categorie == CategorieProdus.ACCESORII || categorie == CategorieProdus.DECORATIUNI || categorie == CategorieProdus.VESTIMENTAR) {
            return new ArticolHandmade(id, denumire, categorie, pret, stoc, areReducereActiva, tipFir, culoare, timpOreLucru);
        }
        throw new InvalidProductDataException("Categoria aleasa nu este valida pentru un articol handmade.");
    }

    @Override
    public Produs getProdus(String id, String denumire, CategorieProdus categorie, double pret, int stoc, boolean areReducereActiva, String nivelDificultate, boolean contineTutorialVideo, int nrPiese) throws InvalidProductDataException {
        if (categorie == CategorieProdus.KIT_DIY) {
            return new KitArtizanat(id, denumire, categorie, pret, stoc, areReducereActiva, nivelDificultate, contineTutorialVideo, nrPiese);
        }
        throw new InvalidProductDataException("Categoria pentru acest kit trebuie sa fie KIT_DIY.");
    }
}
