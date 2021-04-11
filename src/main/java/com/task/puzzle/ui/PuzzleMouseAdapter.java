package com.task.puzzle.ui;

import com.task.puzzle.game.PuzzleGame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PuzzleMouseAdapter extends MouseAdapter {
    private final PuzzleGame game;
    private final PuzzleUiConfigurationProperties properties;
    private final JPanel panel;

    public PuzzleMouseAdapter(PuzzleGame game,
                              PuzzleUiConfigurationProperties properties,
                              JPanel panel) {
        this.game = game;
        this.properties = properties;
        this.panel = panel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        final int margin = properties.getMargin();
        final int tileSize = properties.getTileSize();
        final int size = properties.getSize();
        if (game.isGameOver()) {
            game.initiateGame();
            return;
        }

        int ex = e.getX() - margin;
        int ey = e.getY() - margin;

        if (isClickNonOnBoard(ex, ey)) {
            return;
        }

        int c1 = ex / tileSize;
        int r1 = ey / tileSize;
        //convert to 1D
        int clickPos = r1 * size + c1;
        game.move(c1, r1, clickPos);
        panel.repaint();
    }

    private boolean isClickNonOnBoard(int ex, int ey) {
        return ex < 0 || ex > properties.getGridSize() || ey < 0 || ey > properties.getGridSize();
    }
}
