package ro.ase.cozycrafts.family;

/**
 * folosita la inregistrarea si raportarea vanzarilor
 * ordinea constantelor este folosita ca index in vectorul de vanzari lunare
 * si ca index de linie in matricea de bucati vandute in MagazinService
 */
public enum Luna {
    IANUARIE("Ianuarie"),
    FEBRUARIE("Februarie"),
    MARTIE("Martie"),
    APRILIE("Aprilie"),
    MAI("Mai"),
    IUNIE("Iunie"),
    IULIE("Iulie"),
    AUGUST("August"),
    SEPTEMBRIE("Septembrie"),
    OCTOMBRIE("Octombrie"),
    NOIEMBRIE("Noiembrie"),
    DECEMBRIE("Decembrie");

    private final String eticheta;

    Luna(String eticheta) {
        this.eticheta = eticheta;
    }

    /**
     *
     * @return denumirea lunii, folosita la afisare
     */
    public String getEticheta(){
        return eticheta;
    }
}
