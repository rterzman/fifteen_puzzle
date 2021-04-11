package com.task.puzzle.game.valueadapter;

public interface ValueProvider<T> {

    T getNext(T input);
}
