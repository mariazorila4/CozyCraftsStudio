package ro.ase.cozycrafts.storage;

import ro.ase.cozycrafts.factory.ProdusFactory;
import ro.ase.cozycrafts.family.CategorieProdus;
import ro.ase.cozycrafts.family.Produs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFileStorageStrategy implements StorageStrategy{
    private final ProdusFactory factory=new ProdusFactory();

    //metoda care scrie in fisierul text produse
    @Override
    public void salveazaProdus(String caleFisier, List<Produs> produse) throws Exception {
        try(PrintWriter writer=new PrintWriter(new FileWriter(caleFisier))){
            for(Produs p:produse){
                writer.println(p.toCsvFormat());
            }
        }
    }

    //metoda care citeste fisierul text iar apoi incarca produsele in lista
    @Override
    public List<Produs> incarcaProduse(String caleFisier) throws Exception {
        List<Produs> produse=new ArrayList<>();
        File fisier=new File(caleFisier);

        if(!fisier.exists()){
            return produse;
        }

        try(BufferedReader reader=new BufferedReader(new FileReader(fisier))){
            String linie;

            while((linie= reader.readLine())!=null){
                linie=linie.trim();
                if(linie.isEmpty()) continue;

                String[] cuvant=linie.split(";");
                String tip=cuvant[0];
                String id=cuvant[1];
                String denumire=cuvant[2];
                CategorieProdus categorie=CategorieProdus.valueOf(cuvant[3].trim().toUpperCase());
                double pret=Double.parseDouble(cuvant[4]);
                int stoc=Integer.parseInt(cuvant[5]);
                boolean areReducereActiva=Boolean.parseBoolean(cuvant[6]);

                if("HANDMADE".equalsIgnoreCase(tip)){
                    String tipFir=cuvant[7];
                    String culoare=cuvant[8];
                    int timpOreLucru=Integer.parseInt(cuvant[9]);

                    Produs p= factory.getProdus(id, denumire, categorie, pret, stoc, areReducereActiva, tipFir, culoare, timpOreLucru);
                    produse.add(p);
                }else if("KIT".equalsIgnoreCase(tip)){
                 String nivelDificultate=cuvant[7];
                 boolean contineTutorialVideo=Boolean.parseBoolean(cuvant[8]);
                 int nrPiese=Integer.parseInt(cuvant[9]);

                 Produs p=factory.getProdus(id, denumire, categorie, pret, stoc, areReducereActiva, nivelDificultate, contineTutorialVideo, nrPiese);
                 produse.add(p);
                }
            }
        }
        return produse;
    }
}
