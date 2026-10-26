package ro.ase.cozycrafts.exception;

/**
 * Exceptie aruncata la cautarea, modificarea sau stergerea
 * unui produs dupa un ID care nu exista in magazin
 */

public class ProductNotFoundException extends Exception {
    private static final long serialVersionUID=1L;

    /**
     * constructor implicit
     */
    public ProductNotFoundException() {
        super();
    }

    /**
     *
     * @param message descrierea erorii aparute
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}
