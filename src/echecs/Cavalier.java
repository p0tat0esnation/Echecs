package echecs;

public class Cavalier extends Piece {
    public Cavalier(boolean estBlanc) {
        super(estBlanc);
    }

    @Override
    public boolean estMouvementValide(Coordonnees depart, Coordonnees arrivee, Plateau plateau) {
        // Logique de déplacement du cavalier

        if (!plateau.estDansPlateau(arrivee.ligne, arrivee.colonne)) return false;

        int deltaLigne = Math.abs(arrivee.ligne - depart.ligne);
        int deltaColonne = Math.abs(arrivee.colonne - depart.colonne);

        //deplacement en L (1,2 ou 2,1) sans verif de chemin car le cavalier saute pardessus les piece

        return (deltaLigne == 2 && deltaColonne == 1) || (deltaLigne == 1 && deltaColonne == 2);
    }

    @Override
    public String toString() {

        return Character.toString((char) ListePiece.cavalier(estBlanc));
    }

}


