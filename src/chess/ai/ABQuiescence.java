package chess.ai;

import chess.core.Chessboard;
import chess.core.Move;

import java.util.ArrayList;

public class ABQuiescence extends Searcher {
	private int numNodes = 0;
	private int captureNodes = 0;
	private final int win = Integer.MAX_VALUE;
	private final int lose = win * -1;

	@Override
	// Takes in our current board, the eval function we are using, and the depth limit allotted
	// Point of this function is to return the MoveScore which is a particular move and its score
	public MoveScore findBestMove(Chessboard board, BoardEval eval, int depth) {
		setup(board, eval, depth);
		// Alpha starts at worst case, Beta starts at best
		MoveScore result = evalMoves(board, eval, depth, lose, win);
		tearDown();
		System.out.println("Total Nodes: " + numNodes);
		System.out.println("Capture Nodes: " + captureNodes);
		return result;
	}
	
	MoveScore evalMoves(Chessboard board, BoardEval eval, int depth, int alpha, int beta) {
		MoveScore best = null;
		for (Move m: board.getLegalMoves()) {
			numNodes ++;
			Chessboard next = generate(board, m);
			MoveScore result = new MoveScore(-evalBoard(next, eval, depth - 1, -beta, -alpha), m);
			if (best == null || result.getScore() > best.getScore()) {
				best = result;
			}
			alpha = Math.max(result.getScore(), alpha);
			if (alpha >= beta){
				break;
			}
		}
		return best;
	}	
	
	int evalBoard(Chessboard board, BoardEval eval, int depth, int alpha, int beta) {
		// if(the protagonist has lost their king or the board is in checkmate){return the worst possible value}
		if (!board.hasKing(board.getMoverColor()) || board.isCheckmate()) {
			return -eval.maxValue();
		// else if stalemate: it's worse than winning and better than losing
		} else if (board.isStalemate()) {
			return 0;
		// else if you have reached the depth limit, give the score of the terminal node (furthest board you can see)
		} else if (depth == 0) {
			return quiescenceSearch(board, eval, alpha, beta);
		} else {
			return evalMoves(board, eval, depth, alpha, beta).getScore();
		}
	}
	// Quiescence Search implementation https://www.chessprogramming.org/Quiescence_Search
	// Detect when king not on board/was captured. Search no further, tell game over
	int quiescenceSearch(Chessboard board, BoardEval eval, int alpha, int beta){
		if (!board.hasKing(board.getMoverColor()) || board.isCheckmate()) {
			return -eval.maxValue();
		}
		int lowBound = evaluate(board, eval);
		if(lowBound >= beta){
			return beta;
		}
		if(alpha < lowBound){
			alpha = lowBound;
		}
		ArrayList<Move> everyCapture = new ArrayList<>();
		for(Move move : board.getLegalMoves()){
			numNodes ++;
			if(move.captures()){ everyCapture.add(move);}
		}
		if(!everyCapture.isEmpty()) {
			for(Move m : everyCapture){
				captureNodes ++;
				Chessboard next = generate(board, m);
				int score = -quiescenceSearch(next, eval, -beta, -alpha);
				if(score >= beta){
					return beta;
				}
				if(score > alpha){
					alpha = score;
				}
			}
		}
		return alpha;
	}
}
