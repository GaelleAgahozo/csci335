package checkers.evaluators;

import checkers.core.Checkerboard;

import java.util.function.ToIntFunction;

public class NoPreference implements ToIntFunction<Checkerboard> {

    @Override
    public int applyAsInt(Checkerboard value) {
        return 0;
    }
}
