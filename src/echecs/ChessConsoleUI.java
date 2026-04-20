package echecs;

import java.util.Scanner;

/**
 * Implémentation console de ChessUI.
 * Tout ce qui touche System.out et Scanner est ici, et uniquement ici.
 */
public class ChessConsoleUI implements ChessUI {

    private final Scanner scanner = new Scanner(System.in);

    // Étiquettes de colonnes et de lignes pour l'affichage
    private static final String COLONNES = "  a b c d e f g h";

    @Override
    public void afficherPlateau(Plateau plateau) {
        System.out.println();
        System.out.println(COLONNES);
        Piece[][] cases = plateau.getCases();
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < 8; j++) {
                if (cases[i][j] == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(cases[i][j].toString() + " ");
                }
            }
            System.out.println((8 - i));
        }
        System.out.println(COLONNES);
        System.out.println();
    }

    @Override
    public void afficherMessage(String message) {
        System.out.println(message);
    }

    @Override
    public Coordonnees demanderCoordonnees(String type) {
        while (true) {
            System.out.print(type + " (ex: a2) : ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (estFormatValide(input)) {
                int colonne = input.charAt(0) - 'a';
                int ligne   = 8 - Character.getNumericValue(input.charAt(1));
                return new Coordonnees(ligne, colonne);
            }

            System.out.println("Format invalide. Entrez une lettre (a-h) suivie d'un chiffre (1-8), ex: e4.");
        }
    }

    private boolean estFormatValide(String input) {
        if (input.length() != 2) return false;
        char col = input.charAt(0);
        char lig = input.charAt(1);
        return col >= 'a' && col <= 'h' && lig >= '1' && lig <= '8';
    }
}
