package com.task.puzzle;


import com.task.puzzle.game.FifteenPuzzleGame;
import com.task.puzzle.game.PuzzleGame;
import com.task.puzzle.game.valueadapter.RandomIntValueProvider;
import com.task.puzzle.ui.PuzzleUiConfigurationProperties;
import com.task.puzzle.ui.SwingGameUIRunner;

public class PuzzleApplication {
    public static void main(String[] args) {
        final int size = 4;
        final int margin = 30;
        final int dim = 550;

        final PuzzleGame game = new FifteenPuzzleGame(size, new RandomIntValueProvider());
        final PuzzleUiConfigurationProperties properties =
                new PuzzleUiConfigurationProperties(margin, dim, size);

        new SwingGameUIRunner(game, properties).run();
    }
}
