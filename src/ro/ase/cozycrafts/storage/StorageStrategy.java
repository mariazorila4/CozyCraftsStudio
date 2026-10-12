package ro.ase.cozycrafts.storage;

import ro.ase.cozycrafts.family.Produs;

import java.util.List;

public interface StorageStrategy {
    void salveazaProdus(String caleFisier, List<Produs> produse) throws Exception;
    List<Produs> incarcaProduse(String caleFisier) throws Exception;
}
