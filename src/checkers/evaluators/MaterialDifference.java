package checkers.evaluators;

import checkers.core.Checkerboard;

import java.util.function.ToIntFunction;

public class MaterialDifference implements ToIntFunction<Checkerboard> {
    public int applyAsInt(Checkerboard c) {
        // let's get the number of pieces for the current player and the opponent
        return  c.numPiecesOf(c.getCurrentPlayer()) - c.numPiecesOf(c.getCurrentPlayer().opponent());
//        int currentPlayerPieces = c.numPiecesOf(c.getCurrentPlayer());
//        int opponentPieces = c.numPiecesOf(c.getCurrentPlayer().opponent());

        // calculate the difference
//
//        int materialDifference = currentPlayerPieces - opponentPieces;
//        return materialDifference;
    }
}
