package com.task.puzzle.ui;

import com.task.puzzle.game.PuzzleGame;

import javax.swing.*;
import java.awt.*;

public class FifteenPuzzleGameJPanel extends JPanel {
    private static final String START_MSG = "Click start game";
    private static final int ARC_SIZE = 25;
    private static final Color FOREGROUND_COLOR = new Color(134, 121, 121);

    private final PuzzleGame game;
    private final PuzzleUiConfigurationProperties properties;

    public FifteenPuzzleGameJPanel(PuzzleGame game, PuzzleUiConfigurationProperties properties) {
        this.game = game;
        this.properties = properties;

        setPreferredSize(new Dimension(properties.getDimension(), properties.getDimension() + properties.getMargin()));
        setBackground(Color.WHITE);
        setForeground(FOREGROUND_COLOR);
        setFont(new Font("SansSerif", Font.BOLD, 60));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawGrid(graphics2D);
        drawStartMessage(graphics2D);
    }

    public void drawGrid(Graphics2D graphics2D) {
        final int[] titlesField = (int[]) game.getTitlesField();
        final int margin = properties.getMargin();
        final int size = properties.getSize();
        final int tileSize = properties.getTileSize();
        for (int i = 0; i < titlesField.length; i++) {
            int r = i / size;
            int c = i % size;

            int x = properties.getMargin() + c * tileSize;
            int y = margin + r * tileSize;

            if (game.isBlank(titlesField[i])) {
                if (game.isGameOver()) {
                    graphics2D.setColor(FOREGROUND_COLOR);
                    drawCenteredString(graphics2D, "\u2713", x, y);
                }
                continue;
            }

            graphics2D.setColor(getForeground());
            graphics2D.fillRoundRect(x, y, tileSize, tileSize, ARC_SIZE, ARC_SIZE);
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawRoundRect(x, y, tileSize, tileSize, ARC_SIZE, ARC_SIZE);
            graphics2D.setColor(Color.WHITE);

            drawCenteredString(graphics2D, String.valueOf(titlesField[i]), x, y);
        }
    }

    public void drawStartMessage(Graphics2D graphics2D) {
        if (game.isGameOver()) {
            graphics2D.setFont(getFont().deriveFont(Font.PLAIN, 12));
            graphics2D.setColor(FOREGROUND_COLOR);
            graphics2D.drawString(START_MSG, (getWidth() - graphics2D.getFontMetrics().stringWidth(START_MSG)) / 2,
                    getHeight() - properties.getMargin());
        }
    }

    public void drawCenteredString(Graphics2D graphics2D, String msg, int x, int y) {
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int asc = fontMetrics.getAscent();
        int desc = fontMetrics.getDescent();

        graphics2D.drawString(
                msg, x + (properties.getTileSize() - fontMetrics.stringWidth(msg)) /2,
                y + (asc + (properties.getTileSize() - (asc + desc)) / 2));
    }
}
