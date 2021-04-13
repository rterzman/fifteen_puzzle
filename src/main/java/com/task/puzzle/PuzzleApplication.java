package com.task.puzzle;


import com.task.puzzle.game.FifteenPuzzleGame;
import com.task.puzzle.game.PuzzleGame;
import com.task.puzzle.game.position.RandomIntPositionProvider;
import com.task.puzzle.ui.PuzzleUiConfigurationProperties;
import com.task.puzzle.ui.SwingGameUIRunner;

public class PuzzleApplication {
    private static final int GAME_SIZE = 4;
    private static final int MARGIN = 30;
    private static final int DIM = 550;

    public static void main(String[] args) {
        final PuzzleGame game = new FifteenPuzzleGame(GAME_SIZE, new RandomIntPositionProvider());
        final PuzzleUiConfigurationProperties properties =
                new PuzzleUiConfigurationProperties(MARGIN, DIM, GAME_SIZE);
        new SwingGameUIRunner(game, properties).run();
    }
}
