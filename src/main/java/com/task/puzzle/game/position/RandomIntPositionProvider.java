package com.task.puzzle.game.position;

import java.util.Random;

public class RandomIntPositionProvider implements PositionProvider {
    private static final Random RANDOM = new Random();

    @Override
    public Integer getNext(Integer input) {
        return RANDOM.nextInt(input);
    }
}
