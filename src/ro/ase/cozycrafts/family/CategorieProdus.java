package ro.ase.cozycrafts.family;

/**
 * categoriile de produse disponibile in magazinul CozyCrafts Studio
 */

public enum CategorieProdus {
    DECORATIUNI("Decoratiuni"),
    VESTIMENTAR("Articol vestimentar"),
    KIT_DIY("Seturi creatie"),
    ACCESORII("Accesorii");

    private final String eticheta;

    CategorieProdus(String eticheta) {
        this.eticheta = eticheta;
    }

    /**
     * @return denumirea categoriei folosita la afisare
     */
    public String getEticheta() {
        return eticheta;
    }

    /**
     * identifica categoria produsului din text
     * @param text textul citit din fisier sau de la tastatura
     * @return categoria gasita
     * @throws IllegalArgumentException dc textul nu corespunde niciunei categorii
     */
    public static CategorieProdus verificareCategorie(String text){
        for(CategorieProdus c:CategorieProdus.values()){
            if(c.name().equalsIgnoreCase(text.trim()) || c.eticheta.equalsIgnoreCase(text.trim())){
                return c;
            }
        }
        throw new IllegalArgumentException("Categorie necunoscuta: "+text);
    }
}
