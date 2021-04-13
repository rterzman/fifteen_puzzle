package com.task.puzzle.game;

import com.task.puzzle.game.position.PositionProvider;
import lombok.Getter;
import lombok.NonNull;

public class FifteenPuzzleGame implements PuzzleGame {
    private static final int BLANK_POS_VALUE = 0;

    @NonNull
    @Getter
    private final int[] titlesField;
    private final int nTitles;
    private final int size;

    private final PositionProvider positionProvider;

    private int blankPosition;
    private boolean gameOver;

    public FifteenPuzzleGame(int size, PositionProvider positionProvider) {
        this.size = size;
        this.titlesField = new int[size * size];
        this.nTitles = size * size - 1;
        this.positionProvider = positionProvider;
        this.blankPosition = titlesField.length - 1;
        this.gameOver = true;
        initiateGame();
    }

    public PuzzleGame initiateGame() {
        do {
            resetGame();
            shuffle();
        } while (!isSolvable());
        this.gameOver = false;
        return this;
    }

    private void resetGame() {
        for (int i = 0; i < titlesField.length; i++) {
            titlesField[i] = (i + 1) % titlesField.length;
        }
    }

    private void shuffle() {
        int n = nTitles;
        while (n > 1) {
            int randPos = positionProvider.getNext(n--);
            int tmp = titlesField[randPos];
            titlesField[randPos] = titlesField[n];
            titlesField[n] = tmp;
        }
    }

    /**  Only half the permutations of the puzzle are solvable.

        Whenever a tile is preceded by a tile with higher value it counts
        as an inversion. In our case, with the blank space in the home
        position, the number of inversions must be even for the puzzle
        to be solvable.

        See also:
        www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
     */
    private boolean isSolvable() {
        int countInventions = 0;
        for (int i = 0; i < nTitles; i++) {
            for(int j = 0; j < i; j++) {
                if(titlesField[j] > titlesField[i]) {
                    countInventions ++;
                }
            }
        }
        return countInventions %2 == 0;
    }
    
    private boolean isGameFinished() {
        if (titlesField[titlesField.length - 1] != BLANK_POS_VALUE) {
            return false;
        }
        for (int i = nTitles - 1; i >= 0; i--) {
            if (titlesField[i] != i +1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isGameOver() {
        return this.gameOver;
    }

    @Override
    public void move(int c1, int r1, int clickPos) {
        final int cBlank = blankPosition % size;
        final int rBlank = blankPosition / size;
        final int direction = getDirection(c1, r1, cBlank, rBlank);

        if (direction != 0) {
            do {
                final int newBlankPosition = blankPosition + direction;
                titlesField[blankPosition] = titlesField[newBlankPosition];
                blankPosition = newBlankPosition;
            } while (blankPosition != clickPos);
            titlesField[blankPosition] = BLANK_POS_VALUE;
        }
        this.gameOver = isGameFinished();
    }

    @Override
    public boolean isBlank(int val) {
        return val ==  BLANK_POS_VALUE;
    }

    @Override
    public int getBlankPosition() {
        return this.blankPosition;
    }

    private int getDirection(int c1, int r1, int c2, int r2) {
        if (c1 == c2 && Math.abs(r1 - r2) > 0) {
            return (r1 - r2) > 0 ? size : - size;
        } else if (r1 == r2 && Math.abs(c1 - c2) > 0) {
            return (c1 - c2) > 0 ? 1 : -1;
        }
        return 0;
    }
}
