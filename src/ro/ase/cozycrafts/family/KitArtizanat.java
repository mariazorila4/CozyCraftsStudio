package ro.ase.cozycrafts.family;

import ro.ase.cozycrafts.exception.InvalidProductDataException;

/**
 * Kit de creare DIY
 */

public class KitArtizanat extends Produs{
    private static final long serialVersionUID=1L;

    private String nivelDificultate;
    private boolean contineTutorialVideo;
    private int nrPiese;

    /**
     *
     * @param id                    identificatorul produsului
     * @param denumire
     * @param categorie             categoria KIT_DIY
     * @param pret
     * @param stoc
     * @param areReducereActiva     true daca are reducerea aplicata
     * @param nivelDificultate      usor/mediu/greu
     * @param contineTutorialVideo  true dc kitul are tutorial video
     * @param nrPiese               nr de piese din kit (cel putin 2)
     * @throws InvalidProductDataException dc datele sunt invalide
     */
    public KitArtizanat(String id, String denumire, CategorieProdus categorie, double pret, int stoc,
                        boolean areReducereActiva, String nivelDificultate, boolean contineTutorialVideo, int nrPiese) throws InvalidProductDataException {
        super(id, denumire, categorie, pret, stoc, areReducereActiva);

        if(nrPiese<=1){
            throw new InvalidProductDataException("Un kit trebuie sa contina cel putin doua piese");
        }

        this.nivelDificultate=verificaText(nivelDificultate, "nivel dificultate");
        this.contineTutorialVideo=contineTutorialVideo;
        this.nrPiese=nrPiese;
    }

    @Override
    public String getTipProdus() {
        return "KIT";
    }

    /**
     * Format: KIT;id;denumire;categorie;pret;stoc;reducere;nivelDificultate;contineTutorialVideo;nrPiese
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
                nivelDificultate,
                String.valueOf(contineTutorialVideo),
                String.valueOf(nrPiese));
    }

    public String getNivelDificultate() {
        return nivelDificultate;
    }

    public boolean isContineTutorialVideo() {
        return contineTutorialVideo;
    }

    public int getNrPiese() {
        return nrPiese;
    }

    @Override
    public String toString() {
        return super.toString() + " | Kit " + nivelDificultate +
                " (" + nrPiese + " piese, tutorial: "
                + (contineTutorialVideo ? "DA" : "NU") + ")";
    }
}
