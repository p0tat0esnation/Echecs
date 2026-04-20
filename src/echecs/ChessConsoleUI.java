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

    @Override
    public Piece demanderPromotion(boolean estBlanc) {
        System.out.println("Promotion ! Choisissez une pièce :");
        System.out.println("  1 - Dame");
        System.out.println("  2 - Tour");
        System.out.println("  3 - Cavalier");
        System.out.println("  4 - Fou");

        while (true) {
            System.out.print("Votre choix (1-4) : ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": return new Dame(estBlanc);
                case "2": return new Tour(estBlanc);
                case "3": return new Cavalier(estBlanc);
                case "4": return new Fou(estBlanc);
                default:  System.out.println("Choix invalide, entrez 1, 2, 3 ou 4.");
            }
        }
    }

    private boolean estFormatValide(String input) {
        if (input.length() != 2) return false;
        return input.charAt(0) >= 'a' && input.charAt(0) <= 'h'
                && input.charAt(1) >= '1' && input.charAt(1) <= '8';
    }
}
