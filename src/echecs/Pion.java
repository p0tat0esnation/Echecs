
package echecs;

public class Pion extends Piece {
    public Pion(boolean estBlanc) {
        super(estBlanc);
    }

    @Override
    public boolean estMouvementValide(coordonnees depart, coordonnees arrivee, Plateau plateau) {
        // Logique de déplacement du pion (à compléter)
        return false;
    }

    @Override
    public String toString() {
        return Character.toString((char) listepiece.pion(estBlanc));
    }

}
