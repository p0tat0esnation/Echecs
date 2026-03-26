package echecs;

public class listepiece {

    static public final int TOUR_BLANC = 0x2656;
    static public final int TOUR_NOIR = 0x265C;

    static public final int CAV_BLANC = 0x2658;
    static public final int CAV_NOIR = 0x265E;

    static public final int FOU_BLANC = 0x2657;
    static public final int FOU_NOIR = 0x265D;

    static public final int PION_BLANC = 0x2659;
    static public final int PION_NOIR = 0x265F;

    static public final int DAME_BLANC = 0x2655;
    static public final int DAME_NOIR = 0x265B;

    static public final int ROI_BLANC = 0x2654;
    static public final int ROI_NOIR = 0x265A;

    public static int pion(boolean estBlanc) {
        return estBlanc ? PION_BLANC : PION_NOIR;
    }

    public static int tour(boolean estBlanc) {
        return estBlanc ? TOUR_BLANC : TOUR_NOIR;
    }

    public static int cavalier(boolean estBlanc) {
        return estBlanc ? CAV_BLANC : CAV_NOIR;
    }

    public static int fou(boolean estBlanc) {
        return estBlanc ? FOU_BLANC : FOU_NOIR;
    }

    public static int dame(boolean estBlanc) {
        return estBlanc ? DAME_BLANC : DAME_NOIR;
    }

    public static int roi(boolean estBlanc) {
        return estBlanc ? ROI_BLANC : ROI_NOIR;
    }

    public static boolean estPieceBlanche(int code) {
        return code >= 0x2654 && code <= 0x2659;
    }

    public static boolean estPieceNoire(int code) {
        return code >= 0x265A && code <= 0x265F;
    }
}
