package ro.ase.cozycrafts.family;

import ro.ase.cozycrafts.exception.InvalidProductDataException;

public class ArticolHandmade extends Produs{
    private static final long serialVersionUID=1L;

    private String tipFir;
    private String culoare;
    private int timpLucruOre;

    public ArticolHandmade(String id, String denumire, CategorieProdus categorie, double pret, int stoc, boolean areReducereActiva,
                           String tipFir, String culoare, int timpLucruOre) throws InvalidProductDataException {
        super(id, denumire, categorie, pret, stoc, areReducereActiva);

        if(timpLucruOre<0){
            throw new InvalidProductDataException("Timpul de lucru nu poate fi negativ");
        }
        this.tipFir=tipFir;
        this.culoare=culoare;
        this.timpLucruOre=timpLucruOre;
    }

    @Override
    public String getTipProdus() {
        return "HANDMADE";
    }

    //format serializat text pentru fisiere CSV
    @Override
    public String toCsvFormat() {
        return String.join(";",
                getTipProdus(),
                getId(),
                getDenumire(),
                getCategorie().name(),
                String.valueOf(getPret()),
                String.valueOf(getStoc()),
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
