package echecs;

public class Fou extends Piece {
    public Fou(boolean estBlanc) {
        super(estBlanc);
    }

    @Override
    public boolean estMouvementValide(coordonnees depart, coordonnees arrivee, Plateau plateau) {
        // Logique de déplacement du fou (à compléter)
        return false;
    }

    @Override
    public String toString() {
        return Character.toString((char) listepiece.fou(estBlanc));
    }

}