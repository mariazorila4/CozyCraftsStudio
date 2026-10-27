package ro.ase.cozycrafts.storage;

import ro.ase.cozycrafts.exception.InvalidProductDataException;
import ro.ase.cozycrafts.factory.IProdusFactory;
import ro.ase.cozycrafts.factory.ProdusFactory;
import ro.ase.cozycrafts.family.CategorieProdus;
import ro.ase.cozycrafts.family.Produs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Strategie de stocare in fisier text
 * fiecare produs ocupa o linie cu campuri delimitate prin ;
 * <pre>
 * HANDMADE;id;denumire;categorie;pret;stoc;reducere;tipFir;culoare;timpLucruOre
 * KIT;id;denumire;categorie;pret;stoc;reducere;nivelDificultate;contineTutorialVideo;nrPiese
 * </pre>
 */

public class TextFileStorageStrategy implements StorageStrategy{

    private final IProdusFactory factory=new ProdusFactory();

    @Override
    public void salveazaProduse(String caleFisier, List<Produs> produse) throws IOException {
        File fisier=new File(caleFisier);
        //creaza folderul data dc nu exista
        if(fisier.getParentFile()!=null){
            fisier.getParentFile().mkdirs();
        }

        try(PrintStream ps=new PrintStream(fisier)){
            for(Produs p:produse){
                ps.println(p.toCsvFormat());
            }
        }
    }

    //metoda care citeste fisierul text iar apoi incarca produsele in lista
    @Override
    public List<Produs> incarcaProduse(String caleFisier) throws IOException {
        List<Produs> produse=new ArrayList<>();
        File fisier=new File(caleFisier);

        if(!fisier.exists()){
            System.out.println("Fisierul "+caleFisier+" nu exista.");

            return produse;
        }

        try(FileInputStream fluxIn=new FileInputStream(fisier);
            InputStreamReader isr=new InputStreamReader(fluxIn);
            BufferedReader bufferIn=new BufferedReader(isr)){

            String linie;
            int nrLinie=0;

            while((linie= bufferIn.readLine())!=null){
                nrLinie++;
                linie=linie.trim();

                if(linie.isEmpty()){
                    continue;
                }

                try{
                    produse.add(creeazaProdusDinLinie(linie));
                } catch (NumberFormatException e) {
                    System.out.println("Linia "+nrLinie+" ignorata, valoare numerica invalida");
                } catch (InvalidProductDataException | IllegalArgumentException e) {
                    System.out.println("Linia "+nrLinie+" ignorata: "+e.getMessage());
                }
            }
        }
        return produse;
    }

    /**
     * transforma o linie intr-un obiect Produs
     * @param linie citita din fisier
     * @return produsul creat
     * @throws InvalidProductDataException dc linia nu are un format corect sau datele sunt invalide
     * @throws NumberFormatException dc numerele nu pot fi convertite
     */
    private Produs creeazaProdusDinLinie(String linie) throws InvalidProductDataException {
        String[] campuri=linie.split(";");
        if(campuri.length!=10){
            throw  new InvalidProductDataException("Linia trebuie sa aiba 10 campuri, nu "+campuri.length);
        }

        String tip=campuri[0].trim();
        String id=campuri[1].trim();
        String denumire=campuri[2].trim();
        CategorieProdus categorieProdus=CategorieProdus.verificareCategorie(campuri[3]);
        double pret=Double.parseDouble(campuri[4]);
        int stoc=Integer.parseInt(campuri[5]);
        boolean areReducereActiva=citesteBoolean(campuri[6]);

        if("HANDMADE".equalsIgnoreCase(tip)){
            String tipFir=campuri[7];
            String culoare=campuri[8];
            int timpOreLucru=Integer.parseInt(campuri[9].trim());

            return factory.getProdus(id, denumire, categorieProdus, pret, stoc,
                    areReducereActiva, tipFir, culoare, timpOreLucru);

        }else if("KIT".equalsIgnoreCase(tip)){
            String nivelDificultate=campuri[7];
            boolean contineTutorialVideo=citesteBoolean(campuri[8]);
            int nrPiese=Integer.parseInt(campuri[9].trim());

            return factory.getProdus(id, denumire, categorieProdus, pret, stoc,
                    areReducereActiva, nivelDificultate, contineTutorialVideo, nrPiese);
        }

        throw new InvalidProductDataException("Tip de produs necunoscut: "+tip);
    }

    /**
     * converteste "true/false" in boolean, orice alta valoare fiindc considerata invalida
     * @param text citit
     * @return valoarea logica in boolean
     * @throws InvalidProductDataException dc datele sunt invalide
     */
    private boolean citesteBoolean(String text)throws InvalidProductDataException {
        String valoare=text.trim();

        if(valoare.equalsIgnoreCase("true")){
            return true;
        }

        if(valoare.equalsIgnoreCase("false")){
            return false;
        }

        throw new InvalidProductDataException("Valoare logica invalida: "+text);
    }
}
