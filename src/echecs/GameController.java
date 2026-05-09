package echecs;

/**
 * Contrôleur principal du jeu.
 * Contient uniquement la logique métier : tour de jeu, changement de joueur, état de la partie.
 * Ne fait aucun appel à System.out ou Scanner directement.
 */
public class GameController {

    private Plateau plateau;
    private boolean joueurBlanc; // true = blancs, false = noirs
    private ChessUI ui;

    public GameController(ChessUI ui, PieceCustomJson[] piecesCustom) {
        this.plateau = new Plateau();
        this.joueurBlanc = true;
        this.ui = ui;

        for (PieceCustomJson p : piecesCustom) {
            PieceCustom piece = new PieceCustom(p);
            Coordonnees coord = Coordonnees.depuisNotation(p.position); //exemple : a1 -> ligne/colonne
            plateau.placerPiece(coord.ligne, coord.colonne, piece);
        }
    }

    public void lancer() {
        while (true) {
            ui.afficherPlateau(plateau);
            ui.afficherMessage((joueurBlanc ? "Blancs" : "Noirs") + ", à vous de jouer !");

            boolean coupValide = false;
            while (!coupValide) {
                Coordonnees depart  = ui.demanderCoordonnees("Départ");
                Coordonnees arrivee = ui.demanderCoordonnees("Arrivée");

                coupValide = plateau.deplacer(depart, arrivee, joueurBlanc);

                if (!coupValide) {
                    ui.afficherMessage("Coup invalide, recommencez.");
                }
            }

            joueurBlanc = !joueurBlanc;
        }
    }

    // --- Accesseurs utiles pour les futures règles (échec, mat, etc.) ---

    public Plateau getPlateau() {
        return plateau;
    }

    public boolean isJoueurBlanc() {
        return joueurBlanc;
    }
}
