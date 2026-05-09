package echecs;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChessUI ui = new ChessConsoleUI();
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez choisir le choix :");
        String chemin = sc.nextLine().trim();

        PieceCustomJson[] piecesCustom = new PieceCustomJson[0];
        if (!chemin.isEmpty()) {
            piecesCustom = ChargeurPieces.charger(chemin);
        }

        GameController jeu = new GameController(ui, piecesCustom);
    }
}
