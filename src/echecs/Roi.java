package echecs;

public class Roi extends Piece {
    public Roi(boolean estBlanc) {
        super(estBlanc);
    }

    @Override
    public boolean estMouvementValide(coordonnees depart, coordonnees arrivee, Plateau plateau) {
        // Logique de déplacement du roi (à compléter)
        return false;
    }

    @Override
    public String toString() {
        return Character.toString((char) listepiece.roi(estBlanc));
    }

}
