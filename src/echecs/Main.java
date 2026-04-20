package echecs;

public class Main {
    public static void main(String[] args) {
        ChessUI ui = new ChessConsoleUI();
        GameController jeu = new GameController(ui);
        jeu.lancer();
    }
}
