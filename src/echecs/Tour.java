package echecs;

public class Tour extends Piece {
    public Tour(boolean estBlanc) {
        super(estBlanc);
    }

    @Override
    public boolean estMouvementValide(Coordonnees depart, Coordonnees arrivee, Plateau plateau) {
        // Logique de déplacement de la tour (à compléter)
        return false;
    }

    @Override
    public String toString() {
        return Character.toString((char) ListePiece.tour(estBlanc));
    }

}
