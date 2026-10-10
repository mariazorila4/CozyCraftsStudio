package ro.ase.cozycrafts.family;

import ro.ase.cozycrafts.exception.InvalidProductDataException;

public interface IPromotie {
    void aplicaReducere(double procent) throws InvalidProductDataException;
    boolean areReducere();
}
