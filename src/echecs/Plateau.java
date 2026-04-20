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

    public MoveResult deplacer(Coordonnees depart, Coordonnees arrivee, boolean blanc) {
        // Vérifications de base
        if (!estDansPlateau(depart.ligne, depart.colonne))   return MoveResult.INVALIDE;
        if (!estDansPlateau(arrivee.ligne, arrivee.colonne)) return MoveResult.INVALIDE;

        Piece pieceDeplacee = cases[depart.ligne][depart.colonne];
        if (pieceDeplacee == null)                           return MoveResult.INVALIDE;
        if (pieceDeplacee.estBlanc() != blanc)               return MoveResult.INVALIDE;

        // Mouvement interdit par la pièce elle-même
        if (!pieceDeplacee.estMouvementValide(depart, arrivee, this)) return MoveResult.INVALIDE;

        // Impossible de capturer une pièce alliée
        Piece pieceCible = cases[arrivee.ligne][arrivee.colonne];
        if (pieceCible != null && pieceCible.estBlanc() == blanc)     return MoveResult.INVALIDE;

        // --- Simuler le coup ---
        cases[arrivee.ligne][arrivee.colonne] = pieceDeplacee;
        cases[depart.ligne][depart.colonne]   = null;

        // Annuler si le coup laisse son propre roi en échec
        if (estEnEchec(blanc)) {
            cases[depart.ligne][depart.colonne]   = pieceDeplacee;
            cases[arrivee.ligne][arrivee.colonne] = pieceCible;
            return MoveResult.INVALIDE;
        }

        // Promotion : pion arrivé à la dernière rangée
        if (pieceDeplacee instanceof Pion) {
            if ((blanc && arrivee.ligne == 0) || (!blanc && arrivee.ligne == 7)) {
                return MoveResult.PROMOTION;
            }
        }

        return MoveResult.OK;
    }

    /**
     * Remplace la pièce en position par la pièce choisie lors d'une promotion.
     */
    public void promouvoir(Coordonnees position, Piece nouvellePiece) {
        cases[position.ligne][position.colonne] = nouvellePiece;
    }

    // -------------------------------------------------------------------------
    // Détection d'échec
    // -------------------------------------------------------------------------

    /**
     * Retourne true si le roi de la couleur donnée est actuellement en échec.
     */
    public boolean estEnEchec(boolean blanc) {
        Coordonnees posRoi = trouverRoi(blanc);
        if (posRoi == null) return false; // ne devrait pas arriver en jeu normal

        for (int l = 0; l < 8; l++) {
            for (int c = 0; c < 8; c++) {
                Piece p = cases[l][c];
                if (p != null && p.estBlanc() != blanc) {
                    if (p.estMouvementValide(new Coordonnees(l, c), posRoi, this)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Retourne true si le joueur donné est en échec et mat
     * (en échec ET aucun coup légal disponible).
     */
    public boolean estEchecEtMat(boolean blanc) {
        if (!estEnEchec(blanc)) return false;
        return !aUnCoupLegal(blanc);
    }

    /**
     * Retourne true si le joueur donné est pat
     * (pas en échec MAIS aucun coup légal disponible).
     */
    public boolean estPat(boolean blanc) {
        if (estEnEchec(blanc)) return false;
        return !aUnCoupLegal(blanc);
    }

    // -------------------------------------------------------------------------
    // Accesseurs
    // -------------------------------------------------------------------------

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

    // -------------------------------------------------------------------------
    // Méthodes privées
    // -------------------------------------------------------------------------

    private Coordonnees trouverRoi(boolean blanc) {
        for (int l = 0; l < 8; l++) {
            for (int c = 0; c < 8; c++) {
                Piece p = cases[l][c];
                if (p instanceof Roi && p.estBlanc() == blanc) {
                    return new Coordonnees(l, c);
                }
            }
        }
        return null;
    }

    /**
     * Teste si au moins un coup légal existe pour le joueur donné.
     * Un coup est légal s'il ne laisse pas son propre roi en échec.
     */
    private boolean aUnCoupLegal(boolean blanc) {
        for (int lD = 0; lD < 8; lD++) {
            for (int cD = 0; cD < 8; cD++) {
                Piece p = cases[lD][cD];
                if (p == null || p.estBlanc() != blanc) continue;

                Coordonnees depart = new Coordonnees(lD, cD);

                for (int lA = 0; lA < 8; lA++) {
                    for (int cA = 0; cA < 8; cA++) {
                        Coordonnees arrivee = new Coordonnees(lA, cA);

                        // Tenter le coup (on annule si invalide)
                        MoveResult r = deplacer(depart, arrivee, blanc);
                        if (r != MoveResult.INVALIDE) {
                            // Annuler le coup simulé
                            cases[lD][cD] = cases[lA][cA];
                            // Si promotion, on remet un pion pour annuler proprement
                            if (r == MoveResult.PROMOTION) {
                                cases[lD][cD] = new Pion(blanc);
                            }
                            cases[lA][cA] = null;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
