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
