package com.task.puzzle;


import com.task.puzzle.game.FifteenPuzzleGame;
import com.task.puzzle.game.PuzzleGame;
import com.task.puzzle.game.valueadapter.RandomIntValueProvider;
import com.task.puzzle.ui.FifteenPuzzleGameJPanel;
import com.task.puzzle.ui.PuzzleMouseAdapter;
import com.task.puzzle.ui.PuzzleUiConfigurationProperties;

import javax.swing.*;
import java.awt.*;

public class PuzzleApplication {
    private static final String GAME_TITLE = "Fifteen Puzzle Game";

    public static void main(String[] args) {
        final int size = 4;
        final int margin = 30;
        final int dim = 550;

        final PuzzleGame game = new FifteenPuzzleGame(size, new RandomIntValueProvider());
        final PuzzleUiConfigurationProperties properties =
                new PuzzleUiConfigurationProperties(margin, dim, size);
        final FifteenPuzzleGameJPanel puzzleGameJPanel =
                new FifteenPuzzleGameJPanel(game, properties);
        puzzleGameJPanel.addMouseListener(
                new PuzzleMouseAdapter(game, properties, puzzleGameJPanel));

        SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle(GAME_TITLE);
            frame.setResizable(false);
            frame.add(puzzleGameJPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
