package echecs;

public class Dame extends Piece {
    public Dame(boolean estBlanc) {
        super(estBlanc);
    }

    @Override
    public boolean estMouvementValide(Coordonnees depart, Coordonnees arrivee, Plateau plateau) {
        // Logique de déplacement de la dame (à compléter)
        int deltaColonne = arrivee.colonne - depart.colonne;
        int deltaLigne = arrivee.ligne - depart.ligne;

        int direction = estBlanc ? -1 : 1;
        int ligneDepart = estBlanc ? 6 : 1;
        int colonneDepart = 5;

        if (!plateau.estDansPlateau(arrivee.ligne, arrivee.colonne)) {
            return false;
        }
        if (deltaColonne == 0 && deltaLigne == direction && plateau.estCaseVide(arrivee.ligne, arrivee.colonne)) {
            return true;
        }

        // Avance de x
        if (deltaColonne <= 7 && deltaLigne <= 7) {
            int ligneIntermediaire = depart.ligne + direction;
            return plateau.estCaseVide(ligneIntermediaire, depart.colonne)
                    && plateau.estCaseVide(arrivee.ligne, arrivee.colonne);
        }
        return false;
    }

    @Override
    public String toString() {
        return Character.toString((char) ListePiece.dame(estBlanc));
    }

}