package echecs;

public class Dame extends Piece {
    public Dame(boolean estBlanc) {
        super(estBlanc);
    }

    @Override
    public boolean estMouvementValide(coordonnees depart, coordonnees arrivee, Plateau plateau) {
        // Logique de déplacement de la dame (à compléter)
        return false;
    }

    @Override
    public String toString() {
        return Character.toString((char) listepiece.dame(estBlanc));
    }
}