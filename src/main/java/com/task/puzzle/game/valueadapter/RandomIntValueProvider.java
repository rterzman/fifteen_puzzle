package com.task.puzzle.game.valueadapter;

import java.util.Random;

public class RandomIntValueProvider implements ValueProvider<Integer> {
    private static final Random RANDOM = new Random();

    @Override
    public Integer getNext(Integer input) {
        return RANDOM.nextInt(input);
    }
}
