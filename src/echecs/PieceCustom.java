package echecs;

import java.util.List;

public class PieceCustom extends Piece {
    private String nom;
    private String symbole;
    private List<DeplacementJson> deplacements;

    public PieceCustom(PieceCustomJson json) {
        super(json.couleur.equalsIgnoreCase("blanc"));
        this.nom = json.nom;
        this.symbole = json.unicode;

        this.deplacements = json.deplacements;
    }

    @Override
    public String toString() {
        return symbole;
    }

    @Override
    public boolean estMouvementValide(Coordonnees depart, Coordonnees arrivee, Plateau plateau) {
        for (DeplacementJson dep : deplacements) {
            if (dep.type.equals("diagonale") && verifierDiagonale(depart, arrivee, dep.distance, plateau))
                return true;
            if (dep.type.equals("ligne") && verifierLigne(depart, arrivee, dep.distance, plateau))
                return true;
        }
        return false;
    }

    private boolean verifierDiagonale(Coordonnees depart, Coordonnees arrivee, int distance, Plateau plateau) {
        int dL = Math.abs(arrivee.ligne - depart.ligne);
        int dC = Math.abs(arrivee.colonne - depart.colonne);
        if (dL != dC || dL == 0) return false;
        if (distance > 0 && dL != distance) return false; // distance fixe

        // Vérifier chemin libre
        int pasL = Integer.signum(arrivee.ligne - depart.ligne);
        int pasC = Integer.signum(arrivee.colonne - depart.colonne);
        int l = depart.ligne + pasL, c = depart.colonne + pasC;
        while (l != arrivee.ligne || c != arrivee.colonne) {
            if (!plateau.estCaseVide(l, c)) return false;
            l += pasL; c += pasC;
        }
        return true;
    }

    private boolean verifierLigne(Coordonnees depart, Coordonnees arrivee, int distance, Plateau plateau) {
        int dL = Math.abs(arrivee.ligne - depart.ligne);
        int dC = Math.abs(arrivee.colonne - depart.colonne);
        if (dL != 0 && dC != 0) return false; // pas en ligne droite
        int d = dL + dC;
        if (d == 0) return false;
        if (distance > 0 && d != distance) return false; // distance fixe

        // Vérifier chemin libre
        int pasL = Integer.signum(arrivee.ligne - depart.ligne);
        int pasC = Integer.signum(arrivee.colonne - depart.colonne);
        int l = depart.ligne + pasL, c = depart.colonne + pasC;
        while (l != arrivee.ligne || c != arrivee.colonne) {
            if (!plateau.estCaseVide(l, c)) return false;
            l += pasL; c += pasC;
        }
        return true;
    }
}