package echecs;

public class Roi extends Piece {

    public Roi(boolean estBlanc) {
        super(estBlanc);
    }

    @Override
    public boolean estMouvementValide(Coordonnees depart, Coordonnees arrivee, Plateau plateau) {
        if (!plateau.estDansPlateau(arrivee.ligne, arrivee.colonne)) return false;

        int deltaLigne   = Math.abs(arrivee.ligne   - depart.ligne);
        int deltaColonne = Math.abs(arrivee.colonne - depart.colonne);

        // Le roi se déplace d'une seule case dans n'importe quelle direction
        // (diagonale incluse), mais pas sur place
        return deltaLigne <= 1 && deltaColonne <= 1
                && (deltaLigne + deltaColonne > 0);
    }

    @Override
    public String toString() {
        return Character.toString((char) ListePiece.roi(estBlanc));
    }
}
