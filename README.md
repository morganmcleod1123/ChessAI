# Chess AI
A program I created in Java that utilizes the Negamax algorithm to provide a smart chess opponent.
## Project Description
### Negamax and Initial Board Evaluation
To evaluate the strength of potential moves, I utilized the Negamax variant of the Minimax search algorithm. Initially, I used a relatively simple formula
for evaluating the strength of a potential move: a move that would put your score higher than your opponents would be a stronger move.
### Search Optimizations
To make the search run more efficiently, I implemented both alpha-beta pruning as well as a quiescent search.  

Alpha-beta pruning, which skips over evaluation
of provably useless branches of the search tree, greatly helps the overall speed of the search.  

When the Negamax search reaches the depth of moves that it would
normally look ahead to, it checks to see if the last move was a capture or not. If the move was indeed a capture, the search continues to run until the last
move is not a capture. This is known as a quiescent search, and its purpose is to avoid cases in which you fail to see an impending capture that is coming
from just outside of the depth limit.

### Improved Board Evaluation Function
The board evaluation function is the function that looks at a board position and returns a score. This score is used by the Negamax search to find the most optimal next move.  

My initial board evaluation function subtracted the combined value of the player's active pieces by the combined value of the opponents active pieces. While this got the job done, it ignored more subtle strategic advantages like the positions of pieces. To remedy this, I incorporated a table that assigns a value for each square on the chessboard based on what piece occupies it. For example, knights have higher values for squares in the middle of the board. This is because it is generally good positioning to have the knights take the center of the board.  

The values and concept for this board were taken from the Chess Programming Wiki's article on the [Simplified Evaluation Function](https://www.chessprogramming.org/Simplified_Evaluation_Function).

## References
- [Dr. Gabriel Ferrer](https://www.hendrix.edu/mathcs/profile.aspx?id=70718) provided the logic and tests for the chessboard itself. This includes all of the rules and functionality to allow a game between opponents to occur. Additionally, Dr. Ferrer implemented the GUI used by the player.
- I profusely used the [Chess Programming Wiki](https://www.chessprogramming.org/Main_Page) both to understand and implement various chess programming concepts.
