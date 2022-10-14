package chess.ai;

import chess.core.Chessboard;
import chess.core.Move;

import java.util.ArrayList;

public class AlphaBeta extends Searcher {
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
		return result;
	}
	
	MoveScore evalMoves(Chessboard board, BoardEval eval, int depth, int alpha, int beta) {
		MoveScore best = null;
		System.out.println(board.toString());
		// Basically board.getNextBoards()
		for (Move m: board.getLegalMoves()) {
			Chessboard next = generate(board, m);
			// Copied logic for result from checkers AlphaBeta
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
			return evaluate(board, eval);
		} else {
			return evalMoves(board, eval, depth, alpha, beta).getScore();
		}
	}
}
