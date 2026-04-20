package echecs;

public class Cavalier extends Piece {
    public Cavalier(boolean estBlanc) {
        super(estBlanc);
    }

    @Override
    public boolean estMouvementValide(Coordonnees depart, Coordonnees arrivee, Plateau plateau) {
        // Logique de déplacement du cavalier (à compléter)
        
        return false;
    }

    @Override
    public String toString() {
        return Character.toString((char) ListePiece.cavalier(estBlanc));
    }
}
