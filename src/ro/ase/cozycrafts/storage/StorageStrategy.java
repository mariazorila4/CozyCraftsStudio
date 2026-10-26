package ro.ase.cozycrafts.storage;

import ro.ase.cozycrafts.family.Produs;

import java.io.IOException;
import java.util.List;

public interface StorageStrategy {
    /**
     * salveaza lista de produse in fisier
     * @param caleFisier
     * @param produse de salvat
     * @throws IOException dc apare o eroare la scriere
     */
    void salveazaProduse(String caleFisier, List<Produs> produse) throws IOException;

    /**
     * incarca produsele din fisier, liniile invalide fiind ignorate cu mesaj din consola
     * @param caleFisier
     * @return lista de produse citite
     * @throws IOException dc apare o eroare la citire
     */
    List<Produs> incarcaProduse(String caleFisier) throws IOException;
}
