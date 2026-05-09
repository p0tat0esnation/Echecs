package echecs;

public class Coordonnees {
    public int ligne;
    public int colonne;

    public Coordonnees(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public static Coordonnees depuisNotation(String notation) {
        int colonne = notation.charAt(0) - 'a';
        int ligne = 8 - Character.getNumericValue(notation.charAt(1));
        return new Coordonnees(ligne, colonne);
    }
}
