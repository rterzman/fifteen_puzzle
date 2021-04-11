package com.task.puzzle.ui;

import com.task.puzzle.game.PuzzleGame;

import javax.swing.*;
import java.awt.*;

public class SwingGameUIRunner implements GameUIRunner {
    private static final String GAME_TITLE = "Fifteen Puzzle Game";

    private final PuzzleGame game;
    private final PuzzleUiConfigurationProperties properties;

    public SwingGameUIRunner(PuzzleGame game,
                             PuzzleUiConfigurationProperties properties) {
        this.game = game;
        this.properties = properties;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            final FifteenPuzzleGameJPanel puzzleGameJPanel =
                    new FifteenPuzzleGameJPanel(game, properties);
            puzzleGameJPanel.addMouseListener(
                    new PuzzleMouseAdapter(game, properties, puzzleGameJPanel));
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
