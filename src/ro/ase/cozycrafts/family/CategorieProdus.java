package ro.ase.cozycrafts.family;

public enum CategorieProdus {
    DECORATIUNI("Decoratiuni"),
    VESTIMENTAR("Articol vestimentar"),
    KIT_DIY("Seturi creatie"),
    ACCESORII("Accesorii");

    private final String eticheta;

    CategorieProdus(String eticheta) {
        this.eticheta = eticheta;
    }

    public String getEticheta() {
        return eticheta;
    }

    public static CategorieProdus verificareCategorie(String text){
        for(CategorieProdus c:CategorieProdus.values()){
            if(c.name().equalsIgnoreCase(text) || c.eticheta.equalsIgnoreCase(text)){
                return c;
            }
        }
        throw new IllegalArgumentException("Categorie necunoscuta: "+text);
    }
}
