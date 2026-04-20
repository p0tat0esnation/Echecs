package echecs;

public class Tour extends Piece {

    public Tour(boolean estBlanc) {
        super(estBlanc);
    }

    @Override
    public boolean estMouvementValide(Coordonnees depart, Coordonnees arrivee, Plateau plateau) {
        if (!plateau.estDansPlateau(arrivee.ligne, arrivee.colonne)) return false;

        int deltaLigne   = arrivee.ligne   - depart.ligne;
        int deltaColonne = arrivee.colonne - depart.colonne;

        // La tour se déplace en ligne droite : soit même ligne, soit même colonne
        boolean memeColonne = deltaColonne == 0 && deltaLigne != 0;
        boolean memeLigne   = deltaLigne   == 0 && deltaColonne != 0;

        if (!memeColonne && !memeLigne) return false;

        // Vérifier que le chemin est libre (cases intermédiaires)
        int pasLigne   = Integer.signum(deltaLigne);
        int pasColonne = Integer.signum(deltaColonne);

        int l = depart.ligne   + pasLigne;
        int c = depart.colonne + pasColonne;

        while (l != arrivee.ligne || c != arrivee.colonne) {
            if (!plateau.estCaseVide(l, c)) return false;
            l += pasLigne;
            c += pasColonne;
        }

        return true;
    }

    @Override
    public String toString() {
        return Character.toString((char) ListePiece.tour(estBlanc));
    }
}
