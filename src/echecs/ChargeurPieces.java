package echecs;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class ChargeurPieces {
    public static PieceCustomJson[] charger(String cheminFichier){
        Gson gson = new Gson();
        try{
            FileReader reader = new FileReader(cheminFichier);
            return gson.fromJson(reader,PieceCustomJson[].class);
        } catch (IOException e){
            System.out.println("Erreur de chargement : " + e.getMessage());
            return new PieceCustomJson[0]; //tableau vide en cas derreur
        }
    }
}
