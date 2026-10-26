package ro.ase.cozycrafts.family;

import ro.ase.cozycrafts.exception.InvalidProductDataException;

/**
 * Articol crosetat/lucrat manual
 */

public class ArticolHandmade extends Produs{
    private static final long serialVersionUID=1L;

    private String tipFir;
    private String culoare;
    private int timpLucruOre;

    /**
     *
     * @param id                    identificator produs
     * @param denumire              denumire produs
     * @param categorie             categorie (DECORATIUNI, VESTIMENTAR sau ACCESORII)
     * @param pret
     * @param stoc
     * @param areReducereActiva     true dc are reducere activata
     * @param tipFir
     * @param culoare
     * @param timpLucruOre          numarul de ore de lucru (cel putin 1)
     * @throws InvalidProductDataException dc datele sunt invalide
     */
    public ArticolHandmade(String id, String denumire, CategorieProdus categorie, double pret, int stoc,
                           boolean areReducereActiva, String tipFir, String culoare, int timpLucruOre)
            throws InvalidProductDataException {
        super(id, denumire, categorie, pret, stoc, areReducereActiva);

        if(timpLucruOre<=0){
            throw new InvalidProductDataException("Timpul de lucru trebuie sa fie de cel putin o ora.");
        }
        this.tipFir=verificaText(tipFir, "tip fir");
        this.culoare=verificaText(culoare, "culoare");
        this.timpLucruOre=timpLucruOre;
    }

    @Override
    public String getTipProdus() {
        return "HANDMADE";
    }

    /**
     * Format: HANDMADE;id;denumire;categorie;pret;stoc;reducere;tipFir;culoare;timpLucruOre
     */
    @Override
    public String toCsvFormat() {
        return String.join(";",
                getTipProdus(),
                getId(),
                getDenumire(),
                getCategorie().name(),
                String.valueOf(getPret()),
                String.valueOf(getStoc()),
                String.valueOf(areReducere()),
                tipFir,
                culoare,
                String.valueOf(timpLucruOre));
    }

    public String getTipFir() {
        return tipFir;
    }

    public String getCuloare() {
        return culoare;
    }

    public int getTimpLucruOre() {
        return timpLucruOre;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" | Detalii: fir ").append(tipFir);
        sb.append(", culoare ").append(culoare);
        sb.append(", lucru: ").append(timpLucruOre).append(" ore");
        return sb.toString();
    }
}
