package echecs;

public class Fou extends Piece {

    public Fou(boolean estBlanc) {
        super(estBlanc);
    }

    @Override
    public boolean estMouvementValide(Coordonnees depart, Coordonnees arrivee, Plateau plateau) {
        if (!plateau.estDansPlateau(arrivee.ligne, arrivee.colonne)) return false;

        int deltaLigne   = arrivee.ligne   - depart.ligne;
        int deltaColonne = arrivee.colonne - depart.colonne;

        // Le fou se déplace en diagonale : |deltaLigne| == |deltaColonne|
        if (Math.abs(deltaLigne) != Math.abs(deltaColonne)) return false;
        if (deltaLigne == 0) return false; // pas de mouvement

        // Vérifier que le chemin est libre
        int pasLigne   = Integer.signum(deltaLigne);
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
        return Character.toString((char) ListePiece.fou(estBlanc));
    }
}