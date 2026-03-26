package echecs;

public class Plateau {
    private Piece[][] cases;

    public Plateau() {
        cases = new Piece[8][8];
        initialiser();
    }

    private void initialiser() {
        // Initialisation des pièces sur le plateau (à compléter)
        for (int i = 0; i < 8; i++) {
            cases[1][i] = new Pion(true); // Pions blancs
            cases[6][i] = new Pion(false); // Pions noirs
        }
        cases[0][0] = new Tour(true);
        cases[0][7] = new Tour(true);
        cases[7][0] = new Tour(false);
        cases[7][7] = new Tour(false);
        cases[0][1] = new Cavalier(true);
        cases[0][6] = new Cavalier(true);
        cases[7][1] = new Cavalier(false);
        cases[7][6] = new Cavalier(false);
        cases[0][2] = new Fou(true);
        cases[0][5] = new Fou(true);
        cases[7][2] = new Fou(false);
        cases[7][5] = new Fou(false);
        cases[0][3] = new Dame(true);
        cases[0][4] = new Roi(true);
        cases[7][3] = new Dame(false);
        cases[7][4] = new Roi(false);
    }

    public void afficher() {
        // Affichage ASCII du plateau (à compléter)
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (cases[i][j] == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(cases[i][j].toString() + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean deplacer(coordonnees depart, coordonnees arrivee, boolean blanc) {
        // Vérifier validité, déplacer la pièce si possible (à compléter)
        return false;
    }
}
