package echecs;

/**
 * Interface définissant le contrat entre la logique du jeu et l'interface utilisateur.
 * Toute implémentation (console, graphique, web...) doit respecter ce contrat.
 */
public interface ChessUI {

    /**
     * Affiche l'état actuel du plateau.
     */
    void afficherPlateau(Plateau plateau);

    /**
     * Affiche un message à l'utilisateur (erreur, info, changement de tour...).
     */
    void afficherMessage(String message);

    /**
     * Demande une coordonnée à l'utilisateur.
     * @param type label affiché (ex: "Départ", "Arrivée")
     * @return une Coordonnees valide (la validation du format est à la charge de l'implémentation)
     */
    Coordonnees demanderCoordonnees(String type);

    /**
     * Demande au joueur quelle pièce il veut lors d'une promotion.
     * @param estBlanc couleur du joueur qui promeut
     * @return la pièce choisie, prête à être placée sur le plateau
     */
    Piece demanderPromotion(boolean estBlanc);
}
