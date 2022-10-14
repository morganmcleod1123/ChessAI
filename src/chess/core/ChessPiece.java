package chess.core;

public enum ChessPiece {
	PAWN {
		public char symbol() {return 'P';}
		public boolean castsShadow() {return false;}
		public int value() {return 100;}
	}, QUEEN {
		public char symbol() {return 'Q';}
		public boolean castsShadow() {return true;}
		public int value() {return 900;}
	}, ROOK {
		public char symbol() {return 'R';}
		public boolean castsShadow() {return true;}
		public int value() {return 500;}
	}, BISHOP {
		public char symbol() {return 'B';}
		public boolean castsShadow() {return true;}
		public int value() {return 330;}
	}, KNIGHT {
		public char symbol() {return 'N';}
		public boolean castsShadow() {return false;}
		public int value() {return 320;}
	}, KING {
		public char symbol() {return 'K';}
		public boolean castsShadow() {return false;}
		public int value() {return 20000;}
	}, EMPTY {
		public char symbol() {return '-';}
		public boolean castsShadow() {return false;}
		public int value() {return 0;}

	};
	abstract public boolean castsShadow();
	abstract public char symbol();
	abstract public int value();
	
	public final static ChessPiece[] slidingPieces = new ChessPiece[]{QUEEN, ROOK, BISHOP};
}