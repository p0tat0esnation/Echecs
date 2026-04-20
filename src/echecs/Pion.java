
package echecs;

public class Pion extends Piece {
    public Pion(boolean estBlanc) {
        super(estBlanc);
    }

    @Override
    public String toString() {
        return Character.toString((char) ListePiece.pion(estBlanc));
    }

    @Override
    public boolean estMouvementValide(Coordonnees depart, Coordonnees arrivee, Plateau plateau) {
        int deltaLigne = arrivee.ligne - depart.ligne;
        int deltaColonne = arrivee.colonne - depart.colonne;

        int direction = estBlanc ? -1 : 1; // directions pour les pions noirs (1) et blancs (-1)
        int ligneDepart = estBlanc ? 6 : 1; // ligne de départ pr les pions noirs (6) et blancs (1)

        if (!plateau.estDansPlateau(arrivee.ligne, arrivee.colonne))
            return false;

        // Avance simple
        if (deltaColonne == 0 && deltaLigne == direction && plateau.estCaseVide(arrivee.ligne, arrivee.colonne)) {
            return true;
        }

        // Avance double depuis position initiale
        if (deltaColonne == 0 && deltaLigne == 2 * direction && depart.ligne == ligneDepart) {
            int ligneIntermediaire = depart.ligne + direction;
            return plateau.estCaseVide(ligneIntermediaire, depart.colonne)
                    && plateau.estCaseVide(arrivee.ligne, arrivee.colonne);
        }

        // Capture diagonale
        if (Math.abs(deltaColonne) == 1 && deltaLigne == direction) {
            Piece cible = plateau.getPiece(arrivee.ligne, arrivee.colonne);
            return cible != null && cible.estBlanc() != this.estBlanc;
        }

        return false;
    }

}
