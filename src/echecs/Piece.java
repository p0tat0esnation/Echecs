package echecs;

public abstract class Piece {
    protected boolean estBlanc;

    public Piece(boolean estBlanc) {
        this.estBlanc = estBlanc;
    }

    public abstract boolean estMouvementValide(coordonnees depart, coordonnees arrivee, Plateau plateau);

    public boolean estBlanc() {
        return estBlanc;
    }

    public abstract String toString();
}

