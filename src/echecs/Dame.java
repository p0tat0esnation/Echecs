package echecs;

public class Dame extends Piece {

    public Dame(boolean estBlanc) {
        super(estBlanc);
    }

    @Override
    public boolean estMouvementValide(Coordonnees depart, Coordonnees arrivee, Plateau plateau) {
        if (!plateau.estDansPlateau(arrivee.ligne, arrivee.colonne)) return false;

        int deltaLigne   = arrivee.ligne - depart.ligne;
        int deltaColonne = arrivee.colonne - depart.colonne;

        if (deltaLigne == 0 && deltaColonne == 0) return false; // pas de mouvement

        // La dame combine Tour (lignes/colonnes) et Fou (diagonales)
        boolean mouvementTour = deltaLigne == 0 || deltaColonne == 0;
        boolean mouvementFou  = Math.abs(deltaLigne) == Math.abs(deltaColonne);

        if (!mouvementTour && !mouvementFou) return false;

        // Dans les deux cas, le chemin doit être libre
        int pasLigne   = Integer.signum(deltaLigne); //signum = The signum() method returns the sign of a number. A number's sign tells whether it is positive or negative.
        // This method returns 1 for positive numbers, -1 for negative numbers and 0 for the number 0.


        int pasColonne = Integer.signum(deltaColonne);

        int l = depart.ligne   + pasLigne;
        int c = depart.colonne + pasColonne;

        while (l != arrivee.ligne || c != arrivee.colonne) {
            if (!plateau.estCaseVide(l, c)) return false;
            l += pasLigne;
            c += pasColonne;
        }

        return true;
    }

    @Override
    public String toString() {
        return Character.toString((char) ListePiece.dame(estBlanc));
    }
}
