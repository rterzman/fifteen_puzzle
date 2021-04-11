package com.task.puzzle.game;

public interface PuzzleGame {

    /**
     * Initiate a game state
     * Fill filed with values
     * shuffle position values
     */
    PuzzleGame initiateGame();

    /**
     * Game finished checker. It check a game state on resolving chain of positions
     * @return true| false
     */
    boolean isGameOver();

    void move(int c1, int r1, int clickPosition);

    /**
     * This method returns a structure which represents a field data
     * Generic type can't be applied because of primitive data types
     * @return Object.
     */
    Object getTitlesField();

    /**
     * Check if it is a blank position value
     * @param val - position value
     * @return true| false
     */
    boolean isBlank(int val);

    int getBlankPosition();
}
