package ro.ase.cozycrafts.family;

import ro.ase.cozycrafts.exception.InvalidProductDataException;

public class KitArtizanat extends Produs{
    private static final long serialVersionUID=1L;

    private String nivelDificultate;
    private boolean contineTutorialVideo;
    private int nrPiese;

    public KitArtizanat(String id, String denumire, CategorieProdus categorie, double pret, int stoc,
                        boolean areReducereActiva, String nivelDificultate, boolean contineTutorialVideo, int nrPiese) throws InvalidProductDataException {
        super(id, denumire, categorie, pret, stoc, areReducereActiva);

        if(nrPiese<=0){
            throw new InvalidProductDataException("Un kit trebuie sa contina cel putin o piesa");
        }

        this.nivelDificultate=nivelDificultate;
        this.contineTutorialVideo=contineTutorialVideo;
        this.nrPiese=nrPiese;
    }

    @Override
    public String getTipProdus() {
        return "KIT";
    }

    @Override
    public String toCsvFormat() {
        return String.join(";",
                getTipProdus(),
                getId(),
                getDenumire(),
                getCategorie().name(),
                String.valueOf(getPret()),
                String.valueOf(getStoc()),
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
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" | Kit ").append(nivelDificultate);
        sb.append(" (").append(nrPiese).append(" piese");
        sb.append(", tutorial: ").append(contineTutorialVideo ? "DA" : "NU").append(")");
        return sb.toString();
    }
}
