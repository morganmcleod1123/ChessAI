package chess.ai;

import chess.core.BoardSquare;
import chess.core.ChessPiece;
import chess.core.Chessboard;
import chess.core.PieceColor;

import java.util.*;

// Idea for evaluation function from https://www.chessprogramming.org/Simplified_Evaluation_Function

public class SimpleEval implements BoardEval {
	final static int MAX_VALUE = 20000;
	private EnumMap<ChessPiece,Integer> values = new EnumMap<ChessPiece,Integer>(ChessPiece.class);
	private HashMap<ChessPiece, HashMap<BoardSquare, Integer>> pieceSquareValues = new HashMap<>();

	public SimpleEval() {
		values.put(ChessPiece.BISHOP, 330);
		values.put(ChessPiece.KNIGHT, 320);
		values.put(ChessPiece.PAWN, 100);
		values.put(ChessPiece.QUEEN, 900);
		values.put(ChessPiece.ROOK, 500);
		values.put(ChessPiece.KING, MAX_VALUE);


		pieceSquareValues = generatePieceTables();
	}

	@Override
	public int eval(Chessboard board) {
		int total = 0;
		for (BoardSquare s: board.allPieces()) {
			ChessPiece type = board.at(s);
			if (values.containsKey(type)) {
				if (board.colorAt(s).equals(board.getMoverColor())) {
					total += (values.get(type) + pieceSquareValues.get(type).get(s));
				} else {
					total -= (values.get(type) + pieceSquareValues.get(type).get(s));
				}
			}
		}
		return total;
	}

	@Override
	public int maxValue() {
		return MAX_VALUE;
	}
	
	public boolean hasValue(ChessPiece piece) {
		return values.containsKey(piece);
	}
	
	public int valueOf(ChessPiece piece) {
		return values.get(piece);
	}

	public HashMap<ChessPiece, HashMap<BoardSquare, Integer>> generatePieceTables(){
		HashMap<BoardSquare, Integer> pawnMap = new HashMap<>();
		HashMap<BoardSquare, Integer> knightMap = new HashMap<>();
		HashMap<BoardSquare, Integer> bishopMap = new HashMap<>();
		HashMap<BoardSquare, Integer> rookMap = new HashMap<>();
		HashMap<BoardSquare, Integer> queenMap = new HashMap<>();
		HashMap<BoardSquare, Integer> kingMap = new HashMap<>();

		// Painstakingly assign values to squares
		for(BoardSquare s : BoardSquare.values()){
			// Row 8
			if(s.equals(BoardSquare.A8)){pawnMap.put(s, 0);knightMap.put(s, -50);bishopMap.put(s, -20);rookMap.put(s, 0);queenMap.put(s,-20);kingMap.put(s,-30);}
			if(s.equals(BoardSquare.B8)){pawnMap.put(s, 0);knightMap.put(s, -40);bishopMap.put(s, -10);rookMap.put(s, 0);queenMap.put(s,-10);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.C8)){pawnMap.put(s, 0);knightMap.put(s, -30);bishopMap.put(s, -10);rookMap.put(s, 0);queenMap.put(s,-10);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.D8)){pawnMap.put(s, 0);knightMap.put(s, -30);bishopMap.put(s, -10);rookMap.put(s, 0);queenMap.put(s,-5);kingMap.put(s,-50);}
			if(s.equals(BoardSquare.E8)){pawnMap.put(s, 0);knightMap.put(s, -30);bishopMap.put(s, -10);rookMap.put(s, 0);queenMap.put(s,-5);kingMap.put(s,-50);}
			if(s.equals(BoardSquare.F8)){pawnMap.put(s, 0);knightMap.put(s, -30);bishopMap.put(s, -10);rookMap.put(s, 0);queenMap.put(s,-10);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.G8)){pawnMap.put(s, 0);knightMap.put(s, -40);bishopMap.put(s, -10);rookMap.put(s, 0);queenMap.put(s,-10);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.H8)){pawnMap.put(s, 0);knightMap.put(s, -50);bishopMap.put(s, -20);rookMap.put(s, 0);queenMap.put(s,-20);kingMap.put(s,-30);}
			// Row 7
			if(s.equals(BoardSquare.A7)){pawnMap.put(s, 50);knightMap.put(s, -40);bishopMap.put(s, -10);rookMap.put(s, 5);queenMap.put(s,-10);kingMap.put(s,-30);}
			if(s.equals(BoardSquare.B7)){pawnMap.put(s, 50);knightMap.put(s, -20);bishopMap.put(s, 0);rookMap.put(s, 10);queenMap.put(s,0);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.C7)){pawnMap.put(s, 50);knightMap.put(s, 0);bishopMap.put(s, 0);rookMap.put(s, 10);queenMap.put(s,0);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.D7)){pawnMap.put(s, 50);knightMap.put(s, 0);bishopMap.put(s, 0);rookMap.put(s, 10);queenMap.put(s,0);kingMap.put(s,-50);}
			if(s.equals(BoardSquare.E7)){pawnMap.put(s, 50);knightMap.put(s, 0);bishopMap.put(s, 0);rookMap.put(s, 10);queenMap.put(s,0);kingMap.put(s,-50);}
			if(s.equals(BoardSquare.F7)){pawnMap.put(s, 50);knightMap.put(s, 0);bishopMap.put(s, 0);rookMap.put(s, 10);queenMap.put(s,0);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.G7)){pawnMap.put(s, 50);knightMap.put(s, -20);bishopMap.put(s, 0);rookMap.put(s, 10);queenMap.put(s,0);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.H7)){pawnMap.put(s, 50);knightMap.put(s, -40);bishopMap.put(s, -10);rookMap.put(s, 5);queenMap.put(s,-10);kingMap.put(s,-30);}
			// Row 6
			if(s.equals(BoardSquare.A6)){pawnMap.put(s, 10);knightMap.put(s, -30);bishopMap.put(s, -10);rookMap.put(s, -5);queenMap.put(s,-10);kingMap.put(s,-30);}
			if(s.equals(BoardSquare.B6)){pawnMap.put(s, 10);knightMap.put(s, 0);bishopMap.put(s, 0);rookMap.put(s, 0);queenMap.put(s,0);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.C6)){pawnMap.put(s, 20);knightMap.put(s, 10);bishopMap.put(s, 5);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.D6)){pawnMap.put(s, 30);knightMap.put(s, 15);bishopMap.put(s, 10);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-50);}
			if(s.equals(BoardSquare.E6)){pawnMap.put(s, 30);knightMap.put(s, 15);bishopMap.put(s, 10);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-50);}
			if(s.equals(BoardSquare.F6)){pawnMap.put(s, 20);knightMap.put(s, 10);bishopMap.put(s, 5);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.G6)){pawnMap.put(s, 10);knightMap.put(s, 0);bishopMap.put(s, 0);rookMap.put(s, 0);queenMap.put(s,0);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.H6)){pawnMap.put(s, 10);knightMap.put(s, -30);bishopMap.put(s, -10);rookMap.put(s, -5);queenMap.put(s,-10);kingMap.put(s,-30);}
			// Row 5
			if(s.equals(BoardSquare.A5)){pawnMap.put(s, 5);knightMap.put(s, -30);bishopMap.put(s, -10);rookMap.put(s, -5);queenMap.put(s,-5);kingMap.put(s,-30);}
			if(s.equals(BoardSquare.B5)){pawnMap.put(s, 5);knightMap.put(s, 5);bishopMap.put(s, 5);rookMap.put(s, 0);queenMap.put(s,0);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.C5)){pawnMap.put(s, 10);knightMap.put(s, 15);bishopMap.put(s, 5);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.D5)){pawnMap.put(s, 25);knightMap.put(s, 20);bishopMap.put(s, 10);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-50);}
			if(s.equals(BoardSquare.E5)){pawnMap.put(s, 25);knightMap.put(s, 20);bishopMap.put(s, 10);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-50);}
			if(s.equals(BoardSquare.F5)){pawnMap.put(s, 10);knightMap.put(s, 15);bishopMap.put(s, 5);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.G5)){pawnMap.put(s, 5);knightMap.put(s, 5);bishopMap.put(s, 5);rookMap.put(s, 0);queenMap.put(s,0);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.H5)){pawnMap.put(s, 5);knightMap.put(s, -30);bishopMap.put(s, -10);rookMap.put(s, -5);queenMap.put(s,-5);kingMap.put(s,-30);}
			// Row 4
			if(s.equals(BoardSquare.A4)){pawnMap.put(s, 0);knightMap.put(s, -30);bishopMap.put(s, -10);rookMap.put(s, -5);queenMap.put(s,0);kingMap.put(s,-20);}
			if(s.equals(BoardSquare.B4)){pawnMap.put(s, 0);knightMap.put(s, 0);bishopMap.put(s, 0);rookMap.put(s, 0);queenMap.put(s,0);kingMap.put(s,-30);}
			if(s.equals(BoardSquare.C4)){pawnMap.put(s, 0);knightMap.put(s, 15);bishopMap.put(s, 10);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-30);}
			if(s.equals(BoardSquare.D4)){pawnMap.put(s, 20);knightMap.put(s, 20);bishopMap.put(s, 10);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.E4)){pawnMap.put(s, 20);knightMap.put(s, 20);bishopMap.put(s, 10);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-40);}
			if(s.equals(BoardSquare.F4)){pawnMap.put(s, 0);knightMap.put(s, 15);bishopMap.put(s, 10);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-30);}
			if(s.equals(BoardSquare.G4)){pawnMap.put(s, 0);knightMap.put(s, 0);bishopMap.put(s, 0);rookMap.put(s, 0);queenMap.put(s,0);kingMap.put(s,-30);}
			if(s.equals(BoardSquare.H4)){pawnMap.put(s, 0);knightMap.put(s, -30);bishopMap.put(s, -10);rookMap.put(s, -5);queenMap.put(s,-5);kingMap.put(s,-20);}
			// Row 3
			if(s.equals(BoardSquare.A3)){pawnMap.put(s, 5);knightMap.put(s, -30);bishopMap.put(s, -10);rookMap.put(s, -5);queenMap.put(s,-10);kingMap.put(s,-10);}
			if(s.equals(BoardSquare.B3)){pawnMap.put(s, -5);knightMap.put(s, 5);bishopMap.put(s, 10);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-20);}
			if(s.equals(BoardSquare.C3)){pawnMap.put(s, -10);knightMap.put(s, 10);bishopMap.put(s, 10);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-20);}
			if(s.equals(BoardSquare.D3)){pawnMap.put(s, 0);knightMap.put(s, 15);bishopMap.put(s, 10);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-20);}
			if(s.equals(BoardSquare.E3)){pawnMap.put(s, 0);knightMap.put(s, 15);bishopMap.put(s, 10);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-20);}
			if(s.equals(BoardSquare.F3)){pawnMap.put(s, -10);knightMap.put(s, 10);bishopMap.put(s, 10);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,-20);}
			if(s.equals(BoardSquare.G3)){pawnMap.put(s, -5);knightMap.put(s, 5);bishopMap.put(s, 10);rookMap.put(s, 0);queenMap.put(s,0);kingMap.put(s,-20);}
			if(s.equals(BoardSquare.H3)){pawnMap.put(s, 5);knightMap.put(s, -30);bishopMap.put(s, -10);rookMap.put(s, -5);queenMap.put(s,-10);kingMap.put(s,-10);}
			// Row 2
			if(s.equals(BoardSquare.A2)){pawnMap.put(s, 5);knightMap.put(s, -40);bishopMap.put(s, -10);rookMap.put(s, -5);queenMap.put(s,-10);kingMap.put(s,20);}
			if(s.equals(BoardSquare.B2)){pawnMap.put(s, 10);knightMap.put(s, -20);bishopMap.put(s, 5);rookMap.put(s, 0);queenMap.put(s,0);kingMap.put(s,20);}
			if(s.equals(BoardSquare.C2)){pawnMap.put(s, 10);knightMap.put(s, 0);bishopMap.put(s, 0);rookMap.put(s, 0);queenMap.put(s,5);kingMap.put(s,0);}
			if(s.equals(BoardSquare.D2)){pawnMap.put(s, -20);knightMap.put(s, 5);bishopMap.put(s, 0);rookMap.put(s, 0);queenMap.put(s,0);kingMap.put(s,0);}
			if(s.equals(BoardSquare.E2)){pawnMap.put(s, -20);knightMap.put(s, 5);bishopMap.put(s, 0);rookMap.put(s, 0);queenMap.put(s,0);kingMap.put(s,0);}
			if(s.equals(BoardSquare.F2)){pawnMap.put(s, 10);knightMap.put(s, 0);bishopMap.put(s, 0);rookMap.put(s, 0);queenMap.put(s,0);kingMap.put(s,0);}
			if(s.equals(BoardSquare.G2)){pawnMap.put(s, 10);knightMap.put(s, -20);bishopMap.put(s, 5);rookMap.put(s, 0);queenMap.put(s,0);kingMap.put(s,20);}
			if(s.equals(BoardSquare.H2)){pawnMap.put(s, 5);knightMap.put(s, -40);bishopMap.put(s, -10);rookMap.put(s, -5);queenMap.put(s,-10);kingMap.put(s,20);}
			// Row 1
			if(s.equals(BoardSquare.A1)){pawnMap.put(s, 0);knightMap.put(s, -50);bishopMap.put(s, -20);rookMap.put(s, 0);queenMap.put(s,-20);kingMap.put(s,20);}
			if(s.equals(BoardSquare.B1)){pawnMap.put(s, 0);knightMap.put(s, -40);bishopMap.put(s, -10);rookMap.put(s, 0);queenMap.put(s,-10);kingMap.put(s,30);}
			if(s.equals(BoardSquare.C1)){pawnMap.put(s, 0);knightMap.put(s, -30);bishopMap.put(s, -10);rookMap.put(s, 0);queenMap.put(s,-10);kingMap.put(s,10);}
			if(s.equals(BoardSquare.D1)){pawnMap.put(s, 0);knightMap.put(s, -30);bishopMap.put(s, -10);rookMap.put(s, 5);queenMap.put(s,-5);kingMap.put(s,0);}
			if(s.equals(BoardSquare.E1)){pawnMap.put(s, 0);knightMap.put(s, -30);bishopMap.put(s, -10);rookMap.put(s, 5);queenMap.put(s,-5);kingMap.put(s,0);}
			if(s.equals(BoardSquare.F1)){pawnMap.put(s, 0);knightMap.put(s, -30);bishopMap.put(s, -10);rookMap.put(s, 0);queenMap.put(s,-10);kingMap.put(s,10);}
			if(s.equals(BoardSquare.G1)){pawnMap.put(s, 0);knightMap.put(s, -40);bishopMap.put(s, -10);rookMap.put(s, 0);queenMap.put(s,-10);kingMap.put(s,30);}
			if(s.equals(BoardSquare.H1)){pawnMap.put(s, 0);knightMap.put(s, -50);bishopMap.put(s, -20);rookMap.put(s, 0);queenMap.put(s,-20);kingMap.put(s,20);}
		}
		HashMap<ChessPiece, HashMap<BoardSquare, Integer>> pieceValueTable = new HashMap<>();
		pieceValueTable.put(ChessPiece.PAWN, pawnMap);
		pieceValueTable.put(ChessPiece.KNIGHT, knightMap);
		pieceValueTable.put(ChessPiece.BISHOP, bishopMap);
		pieceValueTable.put(ChessPiece.ROOK, rookMap);
		pieceValueTable.put(ChessPiece.QUEEN, queenMap);
		pieceValueTable.put(ChessPiece.KING, kingMap);
		return pieceValueTable;
	}




}
