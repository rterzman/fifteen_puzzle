package com.task.puzzle.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PuzzleUiConfigurationPropertiesTest {
    private static final int MARGIN = 30;
    private static final int DIM = 550;
    private static final int SIZE = 4;

    @Test
    void shouldCreateConfigurationProperties() {
        final int expectedGridSize = DIM - 2 * MARGIN;
        final int expectedTileSize = expectedGridSize / SIZE;

        final PuzzleUiConfigurationProperties testable = new PuzzleUiConfigurationProperties(MARGIN, DIM, SIZE);

        assertThat(testable).isNotNull()
                .satisfies(t -> assertThat(t.getGridSize()).isEqualTo(expectedGridSize))
                .satisfies(t -> assertThat(t.getTileSize()).isEqualTo(expectedTileSize));
    }

    @Test
    void shouldThrowAnExceptionWithIncorrectSize() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new PuzzleUiConfigurationProperties(MARGIN, DIM, 1));
    }

}