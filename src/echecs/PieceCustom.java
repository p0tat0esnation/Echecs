package echecs;


import java.util.List;

public class PieceCustom extends Piece {
    private String nom;
    private String symbole;
    private List<DeplacementJson> deplacements;

    @Override
    public boolean estMouvementValide(Coordonnees depart, Coordonnees arrivee, Plateau plateau) {
        for (DeplacementJson dep :  deplacements) {
            if (dep.type.equals("diagonale") && verifierDiagonale(depart,arrivee,dep.distance,plateau)){
                return true;
            }
            if (dep.type.equals("ligne") && verifierLigne(depart,arrivee,dep.distance,plateau)){
                return true;
            }

        }
        return false;
    }
}
