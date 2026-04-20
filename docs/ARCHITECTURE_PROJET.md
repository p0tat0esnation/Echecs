# Architecture du projet Echecs

Ce document explique le projet pour qu'une personne debutante puisse le reprendre, comprendre ce qui existe deja, et savoir quoi faire ensuite.

## 1) Vue d'ensemble

Le projet est une application Java console qui simule une partie d'echecs en tour par tour.

- Point d'entree: Main.main
- Controle de partie: Chess
- Etat du plateau: Plateau
- Regles de deplacement: Piece (abstraite) + classes filles (Pion, Tour, Fou, Cavalier, Dame, Roi)
- Types utilitaires: Coordonnees, Move, ListePiece

## 2) Arborescence logique

- Main
  - Cree une instance de Chess et lance la boucle de jeu.
- Chess
  - Gere l'alternance des joueurs.
  - Demande les coordonnees de depart et d'arrivee.
  - Delegue la validation du coup a Plateau.deplacer.
- Plateau
  - Stocke les pieces dans une grille Piece[8][8].
  - Initialise les pieces au demarrage.
  - Verifie les contraintes generales d'un coup (piece presente, couleur du joueur, pas de capture alliee).
  - Delegue la regle fine de deplacement a la piece concernee via le polymorphisme.
- Piece (abstraite)
  - Definit le contrat: estMouvementValide(depart, arrivee, plateau).
- Classes de pieces
  - Implementent chacune leurs regles (incomplet actuellement sauf Pion, et Dame partiellement mais incorrecte).

## 3) Flux d'un coup (runtime)

1. Main.main appelle Chess.lancer.
2. Chess.lancer affiche le plateau puis demande une case de depart et d'arrivee.
3. Chess.lancer appelle Plateau.deplacer(depart, arrivee, joueurBlanc).
4. Plateau.deplacer:
   - verifie qu'une piece existe sur la case depart,
   - verifie que la piece appartient au joueur courant,
   - appelle piece.estMouvementValide(...),
   - refuse la capture d'une piece alliee,
   - applique le deplacement si tout est valide.
5. Chess inverse le joueur et continue la boucle.

## 4) Detail des classes

### Main

- Fichier: src/echecs/Main.java
- Role: lancer l'application.
- Niveau de completion: correct et minimal.

### Chess

- Fichier: src/echecs/Chess.java
- Role:
  - boucle de jeu infinie,
  - saisie utilisateur,
  - affichage,
  - alternance des joueurs.
- Points importants:
  - demandeCoordonnees valide le format a1..h8,
  - conversion en indices tableau (ligne 0 en haut).
- Limites actuelles:
  - Scanner recree a chaque saisie,
  - aucune condition de fin (echec mat, abandon, pat, etc.),
  - pas de commandes speciales (quitter, annuler).

### Plateau

- Fichier: src/echecs/Plateau.java
- Role:
  - source de verite du placement des pieces,
  - initialisation standard,
  - validation generale d'un coup,
  - affichage texte.
- Limites actuelles:
  - aucune logique d'echec/echec mat,
  - aucun historique de coups,
  - pas de promotions, roque, prise en passant.

### Piece (abstraite)

- Fichier: src/echecs/Piece.java
- Role: API commune pour toutes les pieces.
- Attribut principal: estBlanc.

### Pion

- Fichier: src/echecs/Pion.java
- Etat: c'est la piece la plus complete.
- Regles implementees:
  - avance simple,
  - avance double depuis la ligne de depart,
  - capture diagonale.
- Regles manquantes:
  - prise en passant,
  - promotion.

### Tour, Fou, Cavalier, Roi

- Fichiers: src/echecs/Tour.java, src/echecs/Fou.java, src/echecs/Cavalier.java, src/echecs/Roi.java
- Etat: non implementes (estMouvementValide retourne false).

### Dame

- Fichier: src/echecs/Dame.java
- Etat: implementation presente mais incorrecte.
- Pourquoi:
  - la logique ressemble partiellement a celle d'un pion,
  - absence de vrai controle horizontal/vertical/diagonal,
  - variables inutilisees.

### Coordonnees

- Fichier: src/echecs/Coordonnees.java
- Role: petit objet de donnees (ligne, colonne).
- Remarque: champs publics, ce qui est simple mais moins encapsule.

### Move

- Fichier: src/echecs/Move.java
- Role: modele un coup (depart, arrivee).
- Etat: classe prete mais pas encore utilisee dans la logique actuelle.

### ListePiece

- Fichier: src/echecs/ListePiece.java
- Role: centralise les symboles Unicode et utilitaires de couleur.
- Remarque: les constantes "BLANC/NOIR" semblent inversees visuellement par rapport aux noms, mais cela n'empeche pas l'affichage.

## 5) Statut actuel du projet

Etat global: prototype jouable tres partiel.

Fonctionne deja:
- lancement de la boucle de jeu,
- affichage du plateau,
- alternance des tours,
- validation de base (piece existante, bonne couleur, pas de capture alliee),
- deplacements de pion.

Bloquant pour une vraie partie d'echecs:
- Tour/Fou/Cavalier/Roi non implementes,
- Dame incorrecte,
- aucune regle d'echec,
- aucune condition de victoire/fin,
- regles speciales absentes (roque, promotion, prise en passant).

Contraintes de verification dans cet environnement:
- impossible de compiler ici car aucun JDK n'est installe sur la machine d'execution de l'agent.

## 6) Priorites pour continuer le projet

Ordre conseille:

1. Corriger Dame + implementer Tour/Fou/Cavalier/Roi.
2. Ajouter une methode utilitaire pour verifier qu'un chemin est libre (utile Tour/Fou/Dame).
3. Centraliser les validations de mouvement pour eviter les duplications.
4. Ajouter detection de "roi en echec".
5. Interdire les coups qui laissent son propre roi en echec.
6. Ajouter echec et mat / pat / abandon / match nul.
7. Ajouter roque, promotion, prise en passant.
8. Introduire une classe de service de regles pour separer I/O console et logique metier.

## 7) Strategie de reprise pour debutant

- Commencer par des tests manuels simples piece par piece.
- Implementer une piece a la fois, puis verifier visuellement sur le plateau.
- Eviter d'ajouter plusieurs regles complexes en meme temps.
- Introduire progressivement des tests automatiques (JUnit) des que les mouvements de base sont stables.

## 8) Liens avec le schema graphique

Le schema principal est dans:
- docs/diagrammes/architecture.mmd

Ce fichier peut etre rendu dans un lecteur Mermaid pour visualiser les dependances entre classes et les appels de fonctions principaux.
