package echecs;

public class Move {
    private Coordonnees depart;
    private Coordonnees arrivee;

    public Move(Coordonnees depart, Coordonnees arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
    }

    public Coordonnees getDepart() {
        return depart;
    }

    public Coordonnees getArrivee() {
        return arrivee;
    }

}