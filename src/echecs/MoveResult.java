package echecs;

/**
 * Résultat d'une tentative de déplacement.
 */
public enum MoveResult {
    /** Coup illégal (mouvement invalide, roi en échec après, pièce alliée...) */
    INVALIDE,

    /** Coup valide, jeu continue normalement. */
    OK,

    /** Coup valide et un pion a atteint la dernière rangée : promotion requise. */
    PROMOTION
}
