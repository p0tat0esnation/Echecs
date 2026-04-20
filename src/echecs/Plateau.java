package echecs;

public class Plateau {

    private Piece[][] cases;

    public Plateau() {
        cases = new Piece[8][8];
        initialiser();
    }

    private void initialiser() {
        // Pions
        for (int i = 0; i < 8; i++) {
            cases[1][i] = new Pion(false); // noirs
            cases[6][i] = new Pion(true);  // blancs
        }
        // Tours
        cases[0][0] = new Tour(false);
        cases[0][7] = new Tour(false);
        cases[7][0] = new Tour(true);
        cases[7][7] = new Tour(true);
        // Cavaliers
        cases[0][1] = new Cavalier(false);
        cases[0][6] = new Cavalier(false);
        cases[7][1] = new Cavalier(true);
        cases[7][6] = new Cavalier(true);
        // Fous
        cases[0][2] = new Fou(false);
        cases[0][5] = new Fou(false);
        cases[7][2] = new Fou(true);
        cases[7][5] = new Fou(true);
        // Dames et rois (dame sur sa couleur : dame blanche en d1=col3, dame noire en d8=col3)
        cases[0][3] = new Dame(false); // dame noire en d8
        cases[0][4] = new Roi(false);  // roi  noir  en e8
        cases[7][3] = new Dame(true);  // dame blanche en d1
        cases[7][4] = new Roi(true);   // roi  blanc  en e1
    }

    public boolean deplacer(Coordonnees depart, Coordonnees arrivee, boolean blanc) {
        if (cases[depart.ligne][depart.colonne] == null) return false;
        if (cases[depart.ligne][depart.colonne].estBlanc() != blanc) return false;

        if (!cases[depart.ligne][depart.colonne].estMouvementValide(depart, arrivee, this)) return false;

        // Interdit de capturer une pièce alliée
        if (cases[arrivee.ligne][arrivee.colonne] != null
                && cases[arrivee.ligne][arrivee.colonne].estBlanc() == blanc) return false;

        cases[arrivee.ligne][arrivee.colonne] = cases[depart.ligne][depart.colonne];
        cases[depart.ligne][depart.colonne] = null;
        return true;
    }

    // --- Accesseurs ---

    /**
     * Retourne la grille brute. Utilisé par l'UI pour l'affichage.
     * Ne pas modifier le tableau retourné.
     */
    public Piece[][] getCases() {
        return cases;
    }

    public Piece getPiece(int ligne, int colonne) {
        if (!estDansPlateau(ligne, colonne)) return null;
        return cases[ligne][colonne];
    }

    public boolean estDansPlateau(int ligne, int colonne) {
        return ligne >= 0 && ligne < 8 && colonne >= 0 && colonne < 8;
    }

    public boolean estCaseVide(int ligne, int colonne) {
        return getPiece(ligne, colonne) == null;
    }
}
