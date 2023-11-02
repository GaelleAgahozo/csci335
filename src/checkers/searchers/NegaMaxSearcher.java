package checkers.searchers;

import  checkers.core.Checkerboard;
import checkers.core.CheckersSearcher;
import checkers.core.Move;
import checkers.core.PlayerColor;
import core.Duple;

import java.util.Optional;
import java.util.function.ToIntFunction;

//implement a specific search algorithm
public class NegaMaxSearcher extends CheckersSearcher {
    private int numNodes = 0;

    public NegaMaxSearcher (ToIntFunction<Checkerboard> e) {
        super(e);
    }

    @Override
    public int numNodesExpanded() {
        return numNodes;
    }

    @Override
    public Optional<Duple<Integer, Move>> selectMove(Checkerboard board) {
        int bestScore = -Integer.MAX_VALUE;
        PlayerColor startingPlayer = board.getCurrentPlayer();

        Move bestMove = null;
        Optional<Duple<Integer, Move>> best = Optional.empty();
        for (Checkerboard alternative: board.getNextBoards()) {
            int negation = -1;
            numNodes += 1;
            int score = negaMax(alternative, getDepthLimit() - 1, negation, startingPlayer);
            if (best.isEmpty() ||  best.get().getFirst() < score) {
                best = Optional.of(new Duple<>(score, alternative.getLastMove()));
            }
//            if (score > bestScore){
//                bestScore = score;
//                bestMove = alternative.getLastMove();
//            }

//
//            int negation = board.getCurrentPlayer() != alternative.getCurrentPlayer() ? -1 : 1;
//            int scoreFor = negation * getEvaluator().applyAsInt(alternative);

        }
        return best;

//        return bestMove == null? Optional.empty(): Optional.of(new Duple<>(bestScore, bestMove));
    }
    private int negaMax(Checkerboard board, int depth, int negation,  PlayerColor startingPlayer){

        // base case for recursion: if depth is 0 or the game is over, then return the boards evaluation
        PlayerColor lastColor = startingPlayer;
        startingPlayer = board.getCurrentPlayer();
        negation = lastColor != startingPlayer ? -1 : 1;

        if(depth <= 0 || board.gameOver()){
            int boardEvaluation = negation * getEvaluator().applyAsInt(board);
            return boardEvaluation;
        }
        int maxScore = -Integer.MIN_VALUE;
        for (Checkerboard altenative : board.getNextBoards()){

            numNodes += 1;
            //apply the negation on the player's turn
            int score =  -negaMax(altenative, depth -1, negation, startingPlayer);
//            int score = -negaMax(altenative, depth -1);
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;

}
}
