package ro.ase.cozycrafts.family;

import ro.ase.cozycrafts.exception.InvalidProductDataException;

/**
 * Interfata creata pt a implementa metode care sa aplice reduceri produselor
 */

public interface IPromotie {

    /**
     * reduce pretul produsului
     * @param procent procentul de reducere intre 1 si 50%
     * @throws InvalidProductDataException dc procentul nu face parte din interval sau dc produsul este deja redus
     */
    void aplicaReducere(double procent) throws InvalidProductDataException;

    /**
     *
     * @return true dc produsul are o reducere activa
     */
    boolean areReducere();
}
