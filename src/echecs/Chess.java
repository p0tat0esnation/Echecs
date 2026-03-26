package Echecs;

public class Chess {
	
	public static void main(String[] args) {
		new Chess().execute();
	}

	static public final int ROOK_WHITE = 0x2656; 
	static public final int ROOK_BLACK = 0x265C;
	
	static public final int KNIGHT_WHITE = 0x2658;
	static public final int KNIGHT_BLACK = 0x265E;
	
	static public final int BISHOP_WHITE = 0x2657;
	static public final int BISHOP_BLACK = 0x265D;
	
	static public final int PAWN_WHITE = 0x2659;
	static public final int PAWN_BLACK = 0x265F;
	
	static public final int QUEEN_WHITE = 0x2655;
	static public final int QUEEN_BLACK = 0x265B;
	
	static public final int KING_WHITE = 0x2656;
	static public final int KING_BLACK = 0x265A;
	
	private void execute() 
	{
		// 
		System.out.println("8|"+show(ROOK_BLACK)+"|"+show(KNIGHT_BLACK)+"|"+show(BISHOP_BLACK)+"|"+show(QUEEN_BLACK)+"|"+show(KING_BLACK)+"|"+show(BISHOP_BLACK)+"|"+show(KNIGHT_BLACK)+"|"+show(ROOK_BLACK)+"|");
		System.out.println("7|"+show(PAWN_BLACK)+"|"+show(PAWN_BLACK)+"|"+show(PAWN_BLACK)+"|"+show(PAWN_BLACK)+"|"+show(PAWN_BLACK)+"|"+show(PAWN_BLACK)+"|"+show(PAWN_BLACK)+"|"+show(PAWN_BLACK)+"|");
		System.out.println("6|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|");
		System.out.println("5|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|");
		System.out.println("4|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|");
		System.out.println("3|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|"+empty()+"|");
		System.out.println("2|"+show(PAWN_WHITE)+"|"+show(PAWN_WHITE)+"|"+show(PAWN_WHITE)+"|"+show(PAWN_WHITE)+"|"+show(PAWN_WHITE)+"|"+show(PAWN_WHITE)+"|"+show(PAWN_WHITE)+"|"+show(PAWN_WHITE)+"|");
		System.out.println("1|"+show(ROOK_WHITE)+"|"+show(KNIGHT_WHITE)+"|"+show(BISHOP_WHITE)+"|"+show(QUEEN_WHITE)+"|"+show(KING_WHITE)+"|"+show(BISHOP_WHITE)+"|"+show(KNIGHT_WHITE)+"|"+show(ROOK_WHITE)+"|");
		System.out.println("  A B C D E F G H");
		
	}
	
	
	private String empty() 
	{
		return " ";
	}


	private String show(int piece)
	{
		return new String(Character.toChars(piece));
	}

}
