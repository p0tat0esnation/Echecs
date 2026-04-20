package echecs;

/**
 * Contrôleur principal du jeu. Zéro I/O.
 */
public class GameController {

    private Plateau plateau;
    private boolean joueurBlanc;
    private final ChessUI ui;

    public GameController(ChessUI ui) {
        this.plateau     = new Plateau();
        this.joueurBlanc = true;
        this.ui          = ui;
    }

    public void lancer() {
        while (true) {
            ui.afficherPlateau(plateau);

            // Vérifier l'état de la partie AVANT le tour du joueur courant
            if (plateau.estEchecEtMat(joueurBlanc)) {
                ui.afficherMessage("Échec et mat ! Les " + couleur(!joueurBlanc) + " gagnent.");
                break;
            }
            if (plateau.estPat(joueurBlanc)) {
                ui.afficherMessage("Pat ! Match nul.");
                break;
            }
            if (plateau.estEnEchec(joueurBlanc)) {
                ui.afficherMessage("Échec au roi des " + couleur(joueurBlanc) + " !");
            }

            ui.afficherMessage(couleur(joueurBlanc) + ", à vous de jouer !");

            // Demander un coup valide
            MoveResult resultat = MoveResult.INVALIDE;
            Coordonnees arrivee = null;

            while (resultat == MoveResult.INVALIDE) {
                Coordonnees depart = ui.demanderCoordonnees("Départ");
                arrivee            = ui.demanderCoordonnees("Arrivée");

                resultat = plateau.deplacer(depart, arrivee, joueurBlanc);

                if (resultat == MoveResult.INVALIDE) {
                    ui.afficherMessage("Coup invalide, recommencez.");
                }
            }

            // Promotion
            if (resultat == MoveResult.PROMOTION) {
                Piece nouvellePiece = ui.demanderPromotion(joueurBlanc);
                plateau.promouvoir(arrivee, nouvellePiece);
            }

            joueurBlanc = !joueurBlanc;
        }
    }

    // -------------------------------------------------------------------------

    private String couleur(boolean blanc) {
        return blanc ? "Blancs" : "Noirs";
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public boolean isJoueurBlanc() {
        return joueurBlanc;
    }
}