package echecs;

public class Chess {
	private Plateau plateau;
	private boolean joueurBlanc; // true = blanc, false = noir

	public Chess() {
		plateau = new Plateau();
		joueurBlanc = true;
	}

	public void lancer() {
        while (true) {
            afficher();
            boolean coupValide = false;
            while (!coupValide) {
                System.out.println((joueurBlanc ? "Blancs" : "Noirs") + ", à vous de jouer !");
                coordonnees depart = demanderCoordonnees("Départ");
                coordonnees arrivee = demanderCoordonnees("Arrivée");
                coupValide = plateau.deplacer(depart, arrivee, joueurBlanc);
                if (!coupValide) {
                    System.out.println("Coup invalide, recommencez.");
					pause(1000); // Petite pause pour éviter de spammer le message
                }
            }
            joueurBlanc = !joueurBlanc;
        }
    }

	private void afficher() {
		plateau.afficher();
	}

	private coordonnees demanderCoordonnees(String type) {
		// À compléter : lecture clavier, gestion flèches, etc.
		return new coordonnees(0, 0); // temporaire
	}

	private void pause(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}