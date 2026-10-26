package ro.ase.cozycrafts.exception;

/**
 * Exceptie aruncata atunci cand datele unui produs nu sunt valide
 * (pret<=0, stoc negativ, campuri goale, format gresit etc)
 */

public class InvalidProductDataException extends Exception {
    private static final long serialVersionUID=1L;

    /**
     * constructor implicit
     */
    public InvalidProductDataException() {
        super();
    }

    /**
     *
     * @param message descrierea erorii aparute
     */
    public InvalidProductDataException(String message) {
        super(message);
    }
}
